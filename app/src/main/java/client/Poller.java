package client;

import java.io.InterruptedIOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import models.data.PollManagerData;
import models.data.Game;
import models.data.Result;
import models.data.User;
import server.GeneralCommand;
import server.PollManager;

public class Poller {
    private Thread pollerThread;
    private CountDownLatch threadDoneSignal;
    private static Poller singleton;

    private static void create() {
        if (singleton == null){
            singleton = new Poller();
        }
    }

    public static void start() {
        create();
        singleton.shutdown();
        singleton.start(0, 3600, false);
    }

    public PollManagerData pollServer() {
        String className = (PollManager.class).toString();
        String methodName = "getChanges";

        GeneralCommand pollCommand = new GeneralCommand(className, methodName, null, null);
        ClientCommunicator communicator = new ClientCommunicator();
        Result result = communicator.send(pollCommand, "10.0.2.2", "8080");
        return result.getPollResult();
    }


    private void runThread(int initialDelaySec, int delaySec, boolean fixedRate) {
        System.out.println("poller starting...");
        boolean initiating = true;
        long sleepTime = delaySec * 1000L;
        while (true) {
            try {
                if (initiating) {
                    if (initialDelaySec > 0) {
                        Thread.sleep(initialDelaySec * 1000L);
                    }
                    initiating = false;
                }
                else if (sleepTime > 0)
                    Thread.sleep(sleepTime);

                long startMillis = System.currentTimeMillis();
                poll();
                sleepTime = fixedRate ? delaySec * 1000L - (System.currentTimeMillis() - startMillis) : delaySec * 1000L;
            }
            catch (InterruptedException e) {
                break;
            }
            catch (InterruptedIOException e) {
                break;
            }
            catch (ClosedByInterruptException e) {
                break;
            }
            catch (Exception e) {
                System.out.println("Error polling" + e.getMessage());
            }
        }
        threadDoneSignal.countDown();
        System.out.println("poller stopping.");
    }

    public void poll() throws Exception {
        ServerProxy server = new ServerProxy();
        PollManagerData pollResult = pollServer();

        ClientModel client = ClientModel.create();

        ArrayList<User> updatedUsers = pollResult.getUsersChanged();
        ArrayList<Game> updatedGames = pollResult.getGamesChanged();

        for (User currUser:updatedUsers) {
           if(currUser.getUsername().compareTo(client.getPlayer().getUsername()) == 0) {
                client.setPlayer(currUser);
                client.addChange(currUser);

           }
        }


        for (Game currUpdatedGame :updatedGames) {
            for (Game currLobbyGame : client.getLobbyGames()) {
                if (currUpdatedGame.getGameName().compareTo(currLobbyGame.getGameName()) == 0) {
                    client.removeLobbyGame(currLobbyGame);
                    client.addLobbyGame(currUpdatedGame);
                }
            }

            if (client.getActiveGame().getGameName().compareTo(currUpdatedGame.getGameName()) == 0) {
                client.setActiveGame(currUpdatedGame);
            }
        }
    }

    public  String getThreadName() {
        return getClass().getName();
    }


    public void start(final int delaySec) {
        start(0, delaySec, false);
    }

    public void start(final int initialDelaySec, final int delaySec, final boolean fixedRate) {
        threadDoneSignal = new CountDownLatch(1);
        pollerThread = new Thread(new Runnable() {
            public  void run() {
                runThread(initialDelaySec, delaySec, fixedRate);
            }
        }, getThreadName());
        pollerThread.start();
        System.out.println("poller thread for " + getThreadName() + " started...");
    }

    public void shutdown() {
        if (pollerThread != null) {
            System.out.println("shutting down... trying to interrupt poller thread...");
            boolean done = false;
            int numTries = 0;
            while (!done) {
                pollerThread.interrupt();
                try {
                    done = threadDoneSignal.await(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    // ingore InterruptedException here
                }
                if (!done) {
                    numTries = numTries + 1;
                    System.out.println("trying to interrupt write thread again " + numTries);
                } else {
                    System.out.println("shutted down successfully.");
                }
            }
        }
    }
}
