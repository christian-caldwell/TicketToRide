package view.presenter;

import android.os.AsyncTask;

import com.example.cs340.tickettoride.GameBoardActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import client.ClientModel;
import models.TTR_Constants;
import models.data.Player;
import models.data.Route;
import view.presenterInterface.IRoutePresenter;

public class RoutePresenter implements IRoutePresenter, Observer {
    private final GameBoardActivity activity;
    private Map<Route, Boolean> hasBeenDrawn;

    public RoutePresenter(GameBoardActivity activity) {
        this.activity = activity;
        hasBeenDrawn = new HashMap<>();
        for (Route r: TTR_Constants.getInstance().getStartingRouteSet()) {
            hasBeenDrawn.put(r, false);
        }
        ClientModel model = ClientModel.create();
        update(model, null);
        model.setRoutePresenter(this);
    }

    @Override
    public Boolean purchase(Route route) {
        System.out.println("Trying to purchase a route!");
        Boolean routeDrawn = hasBeenDrawn.get(route);
        if (routeDrawn == null) {
            System.out.println("ERROR: routeDrawn in purchase method is null...");
        }
        else if (routeDrawn){
            System.out.println("route has already been drawn");
            return false;
        }
        System.out.println("routeDrawn is false. Proceeding");

        Integer purchaseCardColor = route.getCardColor();
        if (purchaseCardColor == TTR_Constants.getInstance().WILD) {
            purchaseCardColor = TTR_Constants.getInstance().RED;
            //purchaseCardColor = activity.getPurchaseCardColor();
        }

        Integer numWilds = 0;
        //Integer numWilds = activity.getPurchaseNumberWilds();

        // check if the player has the necessary cards for the purchase
        ClientModel model = ClientModel.create();
        Map<Integer, Integer> ticketHand = model.getPlayer().getTickets();
        if (ticketHand.get(purchaseCardColor) + numWilds >= route.findLength() &&
                numWilds <= ticketHand.get(TTR_Constants.getInstance().WILD) &&
                model.getPlayer().getTrainsRemaining() >= route.findLength()) {
            //FIXME: add the purchasing color to the purchaseRoute method
            System.out.println("Purchasing route from ClientModel");
            model.purchaseRoute(route, numWilds);
            return true; //This should probably reflect the success of the method call...
        }
        if (!(ticketHand.get(purchaseCardColor) + numWilds >= route.findLength())) {
            System.out.println("Not enough cards");
            System.out.print(ticketHand.get(purchaseCardColor));
            System.out.print(" + ");
            System.out.print(numWilds);
            System.out.print(" < ");
            System.out.println(route.findLength());

        }
        if (!(numWilds <= ticketHand.get(TTR_Constants.getInstance().WILD))) {
            System.out.println("You don't have that many wild cards");
        }
        if (!(model.getPlayer().getTrainsRemaining() >= route.findLength())) {
            System.out.println("You don't have enough trains left for that");
        }
        System.out.println("Didn't purchase route...");

        return false;
    }


    @Override
    public void update(Observable o, Object arg) {
        new updateAsyncTask().execute();
    }

    private class updateAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ClientModel model = ClientModel.create();
            List<Player> players = model.getUser().getGame().getPlayers();
            if (players != null) {
                for (Player p: players) {
                    for (Route r: p.getRoutesOwned()) {
                        Boolean rDrawn = hasBeenDrawn.get(r);
                        if (rDrawn == null) {
                            System.out.println("Not finding r in hasBeenDrawn...");
                        }
                        else if (!rDrawn) {
                            Set<Integer> ids = TTR_Constants.routeToIdMap.get(r);
                            activity.draw(ids, p.getPlayerColor());
                            hasBeenDrawn.put(r, true);
                        }
                    }
                }
            }

            return null;
        }
    }
}
