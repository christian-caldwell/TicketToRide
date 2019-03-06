package models;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import models.data.DestinationCard;
import models.data.Enums;
import models.data.Route;
import models.data.TrainCard;

public class Constants {
    public final static String ATLANTA_STRING = "Atlanta";
    public final static String BOSTON_STRING = "Boston";
    public final static String CALGARY_STRING = "Calgary";
    public final static String CHARLESTON_STRING = "Charleston";
    public final static String CHICAGO_STRING = "Chicago";
    public final static String DALLAS_STRING = "Dallas";
    public final static String DENVER_STRING = "Denver";
    public final static String DULUTH_STRING = "Duluth";
    public final static String ELPASO_STRING = "El Paso";
    public final static String HELENA_STRING = "Helena";
    public final static String HOUSTON_STRING = "Houston";
    public final static String KANSASCITY_STRING = "Kansas City";
    public final static String LASVEGAS_STRING = "Las Vegas";
    public final static String LITTLEROCK_STRING = "Little Rock";
    public final static String LOSANGELES_STRING = "Los Angeles";
    public final static String MIAMI_STRING = "Miami";
    public final static String MONTREAL_STRING = "Montreal";
    public final static String NASHVILLE_STRING = "Nashville";
    public final static String NEWORLEANS_STRING = "New Orleans";
    public final static String NEWYORK_STRING = "New York";
    public final static String OMAHA_STRING = "Omaha";
    public final static String OKLAHOMACITY_STRING = "Oklahoma City";
    public final static String PHOENIX_STRING = "Phoenix";
    public final static String PITTSBURGH_STRING = "Pittsburgh";
    public final static String PORTLAND_STRING = "Portland";
    public final static String RALEIGH_STRING = "Raleigh";
    public final static String SAINTLOUIS_STRING = "Saint Louis";
    public final static String SALTLAKECITY_STRING = "Salt Lake City";
    public final static String SANFRANCISCO_STRING = "San Francisco";
    public final static String SANTAFE_STRING = "Sante Fe";
    public final static String SAULTSTMARIE_STRING = "Sault St. Marie";
    public final static String SEATTLE_STRING = "Seattle";
    public final static String TORONTO_STRING = "Toronto";
    public final static String VANCOUVER_STRING = "Vancouver";
    public final static String WASHINGTON_STRING = "Washington";
    public final static String WINNIPEG_STRING = "Winnipeg";

   /*
    public final Route R_VAN_TO_SEA_1 = new Route(1, Enums.Color.WILD , new Pair<String, String>(VANCOUVER_STRING, SEATTLE_STRING));
    public final Route R_VAN_TO_SEA_2 = new Route(1, Enums.Color.WILD , new Pair<String, String>(VANCOUVER_STRING, SEATTLE_STRING));
    public final Route R_SEA_TO_POR_1 = new Route(1, Enums.Color.WILD , new Pair<String, String>(SEATTLE_STRING, PORTLAND_STRING));
    public final Route R_SEA_TO_POR_2 = new Route(1, Enums.Color.WILD , new Pair<String, String>(SEATTLE_STRING, PORTLAND_STRING));
    public final Route R_POR_TO_SAN_1 = new Route(5, Enums.Color.GREEN , new Pair<String, String>(PORTLAND_STRING, SANFRANCISCO_STRING));
    public final Route R_POR_TO_SAN_2 = new Route(5, Enums.Color.PURPLE , new Pair<String, String>(PORTLAND_STRING, SANFRANCISCO_STRING));
    public final Route R_SAN_TO_SLC_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SAN_TO_SLC_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SAN_TO_LA_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SAN_TO_LA_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_VAN_TO_CAL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SEA_TO_CAL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SEA_TO_HEL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_POR_TO_SLC = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_LA_TO_LAS = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_LA_TO_PHO = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_LA_TO_EL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_CAL_TO_WIN = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_CAL_TO_HEL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SLC_TO_HEL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SLC_TO_DEN_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SLC_TO_DEN_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_LA_TO_SLC = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_PHO_TO_EL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_PHO_TO_FE = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_PHO_TO_DEN = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_EL_TO_FE = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_FE_TC_DEN = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DEN_TO_HEL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_HEL_TO_WIN = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_EL_TO_HOU = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_EL_TO_DAL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_EL_TO_OKL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_FE_TO_OKL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DEN_TO_OKL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DEN_TO_KAN_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DEN_TO_KAN_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DEN_TO_OMA = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_HEL_TO_OMA = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_HEL_TO_DUL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_WIN_TO_SSM = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_WIN_TO_DUL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_HOU_TO_DAL_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_HOU_TO_DAL_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DAL_TO_OKL_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DAL_TO_OKL_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_OKL_TO_KAN_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_OKL_TO_KAN_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_KAN_TO_OMA_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_KAN_TO_OMA_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_OMA_TO_DUL_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_OMA_TO_DUL_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DUL_TO_SSM = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_HOU_TO_ORI = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DAL_TO_LIT = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_OKL_TO_LIT = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_KAN_TO_SAI_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_KAN_TO_SAI_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_OMA_TO_CHI = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DUL_TO_CHI = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_DUL_TO_TOR = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SSM_TO_TOR = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SSM_TO_MON = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_ORI_TO_LIT = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_LIT_TO_SAI = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SAI_TO_CHI_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SAI_TO_CHI_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_CHI_TO_TOR = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_TOR_TO_MON = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_ORI_TO_MIA = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_ORI_TO_ATL_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_ORI_TO_ATL_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_LIT_TO_NAS = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SAI_TO_NAS = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_SAI_TO_PIT = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_CHI_TO_PIT_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_CHI_TO_PIT_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_MIA_TO_ATL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_MIA_TO_CHA = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_ATL_TO_CHA = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_ATL_TO_NAS = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_ATL_TO_RAL_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_ATL_TO_RAL_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_NAS_TO_RAL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_NAS_TO_PIT = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_CHA_TO_RAL = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_RAL_TO_WAS_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_RAL_TO_WAS_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_RAL_TO_PIT = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_WAS_TO_PIT = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_WAS_TO_NYC_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_WAS_TO_NYC_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_PIT_TO_NYC_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_PIT_TO_NYC_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_PIT_TO_TOR = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_NYC_TO_MON = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_NYC_TO_BOS_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_NYC_TO_BOS_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_MON_TO_BOS_1 = new Route(, Enums.Color. , new Pair<String, String>(, ));
    public final Route R_MON_TO_BOS_2 = new Route(, Enums.Color. , new Pair<String, String>(, ));*/


    public final static DestinationCard DEN_TO_EL = new DestinationCard(new Pair<String, String>(DENVER_STRING, ELPASO_STRING), 4);
    public final static DestinationCard KAN_TO_HOU = new DestinationCard(new Pair<String, String>(KANSASCITY_STRING, HOUSTON_STRING), 5);
    public final static DestinationCard NYC_TO_ATL = new DestinationCard(new Pair<String, String>(NEWYORK_STRING, ATLANTA_STRING), 6);
    public final static DestinationCard CHI_TO_ORL = new DestinationCard(new Pair<String, String>(CHICAGO_STRING, SALTLAKECITY_STRING), 7);
    public final static DestinationCard CAL_TO_SLC = new DestinationCard(new Pair<String, String>(CALGARY_STRING, SALTLAKECITY_STRING), 7);
    public final static DestinationCard HEL_TO_LA = new DestinationCard(new Pair<String, String>(HELENA_STRING, LOSANGELES_STRING), 8);
    public final static DestinationCard DUL_TO_HOU = new DestinationCard(new Pair<String, String>(DULUTH_STRING, HOUSTON_STRING), 8);
    public final static DestinationCard SSM_TO_NAS = new DestinationCard(new Pair<String, String>(SAULTSTMARIE_STRING, NASHVILLE_STRING), 8);
    public final static DestinationCard MON_TO_ATL = new DestinationCard(new Pair<String, String>(MONTREAL_STRING, ATLANTA_STRING), 9);
    public final static DestinationCard SSM_TO_OKL = new DestinationCard(new Pair<String, String>(SAULTSTMARIE_STRING, OKLAHOMACITY_STRING), 9);
    public final static DestinationCard SEA_TO_LA = new DestinationCard(new Pair<String, String>(SEATTLE_STRING, LOSANGELES_STRING), 9);
    public final static DestinationCard CHI_TO_SAN = new DestinationCard(new Pair<String, String>(CHICAGO_STRING, SANTAFE_STRING), 9);
    public final static DestinationCard DUL_TO_EL = new DestinationCard(new Pair<String, String>(DULUTH_STRING, ELPASO_STRING), 10);
    public final static DestinationCard TOR_TO_MIA = new DestinationCard(new Pair<String, String>(TORONTO_STRING, MIAMI_STRING), 10);
    public final static DestinationCard POR_TO_PHO = new DestinationCard(new Pair<String, String>(PORTLAND_STRING, PHOENIX_STRING), 11);
    public final static DestinationCard DAL_TO_NYC = new DestinationCard(new Pair<String, String>(DALLAS_STRING, NEWYORK_STRING), 11);
    public final static DestinationCard DEN_TO_PIT = new DestinationCard(new Pair<String, String>(DENVER_STRING, PITTSBURGH_STRING), 11);
    public final static DestinationCard WIN_TO_LIT = new DestinationCard(new Pair<String, String>(WINNIPEG_STRING, LITTLEROCK_STRING), 11);
    public final static DestinationCard WIN_TO_HOU = new DestinationCard(new Pair<String, String>(WINNIPEG_STRING, HOUSTON_STRING), 12);
    public final static DestinationCard VAN_TO_SAN = new DestinationCard(new Pair<String, String>(VANCOUVER_STRING, SANTAFE_STRING), 13);
    public final static DestinationCard CAL_TO_PHO = new DestinationCard(new Pair<String, String>(CALGARY_STRING, PHOENIX_STRING), 13);
    public final static DestinationCard MON_TO_ORL = new DestinationCard(new Pair<String, String>(MONTREAL_STRING, NEWORLEANS_STRING), 13);
    public final static DestinationCard LA_TO_CHI = new DestinationCard(new Pair<String, String>(LOSANGELES_STRING, CHICAGO_STRING), 16);
    public final static DestinationCard SAN_TO_ATL = new DestinationCard(new Pair<String, String>(SANTAFE_STRING, ATLANTA_STRING), 17);
    public final static DestinationCard POR_TO_NAS = new DestinationCard(new Pair<String, String>(PORTLAND_STRING, NASHVILLE_STRING), 17);
    public final static DestinationCard VAN_NO_MON = new DestinationCard(new Pair<String, String>(VANCOUVER_STRING, MONTREAL_STRING), 20);
    public final static DestinationCard LA_TO_MIA = new DestinationCard(new Pair<String, String>(LOSANGELES_STRING, MIAMI_STRING), 20);
    public final static DestinationCard LA_TO_NYC = new DestinationCard(new Pair<String, String>(LOSANGELES_STRING, NEWYORK_STRING), 21);
    public final static DestinationCard SEA_TO_NYC = new DestinationCard(new Pair<String, String>(SEATTLE_STRING, NEWYORK_STRING), 22);
    public final static DestinationCard BOS_TO_MIA = new DestinationCard(new Pair<String, String>(BOSTON_STRING, MIAMI_STRING), 12);

    public final static int COLORED_TICKET_STARTING_COUNT = 12;
    public final static int WILD_TICKET_STARTING_COUNT = 14;
    public final static int TRAIN_STARTING_COUNT = 48;

    public static Map<Enums.Color, Integer> getStartingTicketDeck(){
        HashMap<Enums.Color, Integer> newDeck = new HashMap<>();
        newDeck.put(Enums.Color.GREEN, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(Enums.Color.RED, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(Enums.Color.YELLOW, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(Enums.Color.BLACK, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(Enums.Color.BLUE, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(Enums.Color.ORANGE, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(Enums.Color.PURPLE, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(Enums.Color.WHITE, COLORED_TICKET_STARTING_COUNT);

        newDeck.put(Enums.Color.WILD, WILD_TICKET_STARTING_COUNT);

        return newDeck;
    }

    public static ArrayList<DestinationCard> getStartingDestinationDeck(){
        ArrayList<DestinationCard> newDeck = new ArrayList<>();

        newDeck.add(DEN_TO_EL);
        newDeck.add(KAN_TO_HOU);
        newDeck.add(NYC_TO_ATL);
        newDeck.add(CHI_TO_ORL);
        newDeck.add(CAL_TO_SLC);
        newDeck.add(HEL_TO_LA);
        newDeck.add(DUL_TO_HOU);
        newDeck.add(SSM_TO_NAS);
        newDeck.add(MON_TO_ATL);
        newDeck.add(SSM_TO_OKL);
        newDeck.add(SEA_TO_LA);
        newDeck.add(CHI_TO_SAN);
        newDeck.add(DUL_TO_EL);
        newDeck.add(TOR_TO_MIA);
        newDeck.add(POR_TO_PHO);
        newDeck.add(DAL_TO_NYC);
        newDeck.add(DEN_TO_PIT);
        newDeck.add(WIN_TO_LIT);
        newDeck.add(WIN_TO_HOU);
        newDeck.add(VAN_TO_SAN);
        newDeck.add(CAL_TO_PHO);
        newDeck.add(MON_TO_ORL);
        newDeck.add(LA_TO_CHI);
        newDeck.add(SAN_TO_ATL);
        newDeck.add(POR_TO_NAS);
        newDeck.add(VAN_NO_MON);
        newDeck.add(LA_TO_MIA);
        newDeck.add(LA_TO_NYC);
        newDeck.add(SEA_TO_NYC);
        newDeck.add(BOS_TO_MIA);

        return newDeck;
    }

    public static Set<Route> getStartingRouteSet(){
        HashSet<Route> newDeck = new HashSet<>();

        return newDeck;
    }

    public static TrainCard getTicket(int colorNumber) {
        switch (colorNumber) {
            case (0): return new TrainCard(Enums.Color.RED);
            case (1): return new TrainCard(Enums.Color.GREEN);
            case (2): return new TrainCard(Enums.Color.BLUE);
            case (3): return new TrainCard(Enums.Color.YELLOW);
            case (4): return new TrainCard(Enums.Color.ORANGE);
            case (5): return new TrainCard(Enums.Color.PURPLE);
            case (6): return new TrainCard(Enums.Color.BLACK);
            case (7): return new TrainCard(Enums.Color.WHITE);
            case (8): return new TrainCard(Enums.Color.WILD);
            default: return null;
        }
    }
}
