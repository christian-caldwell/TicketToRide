package client;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import models.data.Game;
import models.data.Result;
import server.GeneralCommand;
import server.PollManager;

public class Poller {
    private Thread pollerThread;
    private CountDownLatch threadDoneSignal;
    private static Poller singleton;

    public static void end() {
        singleton.shutdown();
    }

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

    public ArrayList<Game> pollServerForGames() {
        String className = PollManager.class.getName();
        String methodName = "getAvailableGames";

        Object[] parameterDataArray = new Object[0];
        Class<?>[] parameterClassArray = new Class<?>[0];


        GeneralCommand newCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

        ClientCommunicator communicator = new ClientCommunicator();

        Result result = communicator.send(newCommand, "10.0.2.2", "8080");
        return result.getPollResult().getGamesChanged();
    }

    public ArrayList<Game> pollServerForStartedGame() {
        String className = PollManager.class.getName();
        String methodName = "checkStarted";

        Object[] parameterDataArray = new Object[0];
        Class<?>[] parameterClassArray = new Class<?>[0];


        GeneralCommand newCommand = new GeneralCommand(className, methodName, parameterClassArray, parameterDataArray);

        ClientCommunicator communicator = new ClientCommunicator();

        Result result = communicator.send(newCommand, "10.0.2.2", "8080");
        return result.getPollResult().getGamesChanged();
    }


    //    We have to use Async tasks here
    private void runThread(int initialDelaySec, int delaySec, boolean fixedRate) {
        System.out.println("poller starting...");
        boolean initiating = true;
        long sleepTime = delaySec;
        while (true) {
            try {
                if (initiating) {
                    if (initialDelaySec > 0) {
                        Thread.sleep(initialDelaySec);
                    }
                    initiating = false;
                }
                else if (sleepTime > 0)
                    Thread.sleep(sleepTime);

                long startMillis = System.currentTimeMillis();
                poll();
                sleepTime = fixedRate ? delaySec - (System.currentTimeMillis() - startMillis) : delaySec;
            }
            catch (InterruptedException e) {
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error polling" + e.getMessage());
            }
        }
        threadDoneSignal.countDown();
        System.out.println("poller stopping.");
    }

    public void poll() {
        ServerProxy server = new ServerProxy();
        ArrayList<Game> pollResult = pollServerForGames();
        ClientModel client = ClientModel.create();

        if (true) {
            /*  Eventually, we will include code to disable this block of code
                This block updates the lobby list with all games, replacing the
                list completely every time. This is needlessly intensive if the
                player is in a game and does not care about the lobby and can be
                disabled by checking the active game in the if statement above
            */
            client.setChangedGameList(pollResult);
            client.update();
            client.setLobbyGamesList(pollResult);
            for (Game game: client.getLobbyGamesList()) {
                for (String userName: game.getPlayerUsernames()) {
                    if (userName.equals(client.getUser().getUsername())) {
                        client.getUser().setGameJoined(game);
                    }
                }
            }
        }

        System.out.println("Current Complete Game List: " + client.getLobbyGamesList().toString());
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
