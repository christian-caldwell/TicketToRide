package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import models.data.DestinationCard;
import models.data.Route;
import models.data.TrainCard;
import server.ServerData;

public class TTR_Constants {
    //    CARD COLORS
    public final Integer EMPTY = 0;
    public final Integer GREEN = 1;
    public final Integer RED = 2;
    public final Integer YELLOW = 3;
    public final Integer BLUE = 4;
    public final Integer ORANGE = 5;
    public final Integer PURPLE = 6;
    public final Integer WHITE = 7;
    public final Integer BLACK = 8;
    public final Integer WILD = 9;

    //    PLAYER COLORS
    public final Integer RED_PLAYER = 1;
    public final Integer GREEN_PLAYER = 2;
    public final Integer BLUE_PLAYER = 3;
    public final Integer YELLOW_PLAYER = 4;
    public final Integer BLACK_PLAYER = 5;
    
    private final String ATLANTA_STRING = "Atlanta";
    private final String BOSTON_STRING = "Boston";
    private final String CALGARY_STRING = "Calgary";
    private final String CHARLESTON_STRING = "Charleston";
    private final String CHICAGO_STRING = "Chicago";
    private final String DALLAS_STRING = "Dallas";
    private final String DENVER_STRING = "Denver";
    private final String DULUTH_STRING = "Duluth";
    private final String ELPASO_STRING = "El Paso";
    private final String HELENA_STRING = "Helena";
    private final String HOUSTON_STRING = "Houston";
    private final String KANSASCITY_STRING = "Kansas City";
    private final String LASVEGAS_STRING = "Las Vegas";
    private final String LITTLEROCK_STRING = "Little Rock";
    private final String LOSANGELES_STRING = "Los Angeles";
    private final String MIAMI_STRING = "Miami";
    private final String MONTREAL_STRING = "Montreal";
    private final String NASHVILLE_STRING = "Nashville";
    private final String NEWORLEANS_STRING = "New Orleans";
    private final String NEWYORK_STRING = "New York";
    private final String OMAHA_STRING = "Omaha";
    private final String OKLAHOMACITY_STRING = "Oklahoma City";
    private final String PHOENIX_STRING = "Phoenix";
    private final String PITTSBURGH_STRING = "Pittsburgh";
    private final String PORTLAND_STRING = "Portland";
    private final String RALEIGH_STRING = "Raleigh";
    private final String SAINTLOUIS_STRING = "Saint Louis";
    private final String SALTLAKECITY_STRING = "Salt Lake City";
    private final String SANFRANCISCO_STRING = "San Francisco";
    private final String SANTAFE_STRING = "Sante Fe";
    private final String SAULTSTMARIE_STRING = "Sault St. Marie";
    private final String SEATTLE_STRING = "Seattle";
    private final String TORONTO_STRING = "Toronto";
    private final String VANCOUVER_STRING = "Vancouver";
    private final String WASHINGTON_STRING = "Washington";
    private final String WINNIPEG_STRING = "Winnipeg";

    private final Route R_VAN_TO_SEA_1 = new Route(1, WILD , new String[] {VANCOUVER_STRING, SEATTLE_STRING});
    private final Route R_VAN_TO_SEA_2 = new Route(1, WILD , new String[] {VANCOUVER_STRING, SEATTLE_STRING});
    private final Route R_SEA_TO_POR_1 = new Route(1, WILD , new String[] {SEATTLE_STRING, PORTLAND_STRING});
    private final Route R_SEA_TO_POR_2 = new Route(1, WILD , new String[] {SEATTLE_STRING, PORTLAND_STRING});
    private final Route R_POR_TO_SAN_1 = new Route(5, GREEN , new String[] {PORTLAND_STRING, SANFRANCISCO_STRING});
    private final Route R_POR_TO_SAN_2 = new Route(5, PURPLE , new String[] {PORTLAND_STRING, SANFRANCISCO_STRING});
    private final Route R_SAN_TO_SLC_1 = new Route(5, ORANGE , new String[] {SANFRANCISCO_STRING, SALTLAKECITY_STRING});
    private final Route R_SAN_TO_SLC_2 = new Route(5, WHITE , new String[] {SANFRANCISCO_STRING, SALTLAKECITY_STRING});
    private final Route R_SAN_TO_LA_1 = new Route(3, YELLOW , new String[] {SANFRANCISCO_STRING, LOSANGELES_STRING});
    private final Route R_SAN_TO_LA_2 = new Route(3, PURPLE , new String[] {SANFRANCISCO_STRING, LOSANGELES_STRING});
    private final Route R_VAN_TO_CAL = new Route(3, WILD , new String[] {VANCOUVER_STRING, CALGARY_STRING});
    private final Route R_SEA_TO_CAL = new Route(4, WILD , new String[] {SEATTLE_STRING, CALGARY_STRING});
    private final Route R_SEA_TO_HEL = new Route(6, YELLOW , new String[] {SEATTLE_STRING, HELENA_STRING});
    private final Route R_POR_TO_SLC = new Route(6, BLUE , new String[] {PORTLAND_STRING, SALTLAKECITY_STRING});
    private final Route R_LA_TO_LAS = new Route(2, WILD , new String[] {LOSANGELES_STRING, LASVEGAS_STRING});
    private final Route R_LA_TO_PHO = new Route(3, WILD , new String[] {LOSANGELES_STRING, PHOENIX_STRING});
    private final Route R_LA_TO_EL = new Route(6, BLACK , new String[] {LOSANGELES_STRING, ELPASO_STRING});
    private final Route R_CAL_TO_WIN = new Route(6, WHITE , new String[] {CALGARY_STRING, WINNIPEG_STRING});
    private final Route R_CAL_TO_HEL = new Route(4, WILD , new String[] {CALGARY_STRING, HELENA_STRING});
    private final Route R_SLC_TO_HEL = new Route(3, PURPLE , new String[] {SALTLAKECITY_STRING, HELENA_STRING});
    private final Route R_SLC_TO_DEN_1 = new Route(3, RED , new String[] {SALTLAKECITY_STRING, DENVER_STRING});
    private final Route R_SLC_TO_DEN_2 = new Route(3, YELLOW , new String[] {SALTLAKECITY_STRING, DENVER_STRING});
    private final Route R_LAS_TO_SLC = new Route(3, ORANGE , new String[] {LASVEGAS_STRING, SALTLAKECITY_STRING});
    private final Route R_PHO_TO_EL = new Route(3, WILD , new String[] {PHOENIX_STRING, ELPASO_STRING});
    private final Route R_PHO_TO_FE = new Route(3, WILD , new String[] {PHOENIX_STRING, SANTAFE_STRING});
    private final Route R_PHO_TO_DEN = new Route(5, WHITE , new String[] {PHOENIX_STRING, DENVER_STRING});
    private final Route R_EL_TO_FE = new Route(2, WILD , new String[] {ELPASO_STRING, SANTAFE_STRING});
    private final Route R_FE_TO_DEN = new Route(2, WILD , new String[] {SANTAFE_STRING, DENVER_STRING});
    private final Route R_DEN_TO_HEL = new Route(4, GREEN , new String[] {DENVER_STRING, HELENA_STRING});
    private final Route R_HEL_TO_WIN = new Route(4, BLUE , new String[] {HELENA_STRING, WINNIPEG_STRING});
    private final Route R_EL_TO_HOU = new Route(6, GREEN , new String[] {ELPASO_STRING, HOUSTON_STRING});
    private final Route R_EL_TO_DAL = new Route(4, RED , new String[] {ELPASO_STRING, DALLAS_STRING});
    private final Route R_EL_TO_OKL = new Route(5, YELLOW , new String[] {ELPASO_STRING, OKLAHOMACITY_STRING});
    private final Route R_FE_TO_OKL = new Route(3, BLUE , new String[] {SANTAFE_STRING, OKLAHOMACITY_STRING});
    private final Route R_DEN_TO_OKL = new Route(4, RED , new String[] {DENVER_STRING, OKLAHOMACITY_STRING});
    private final Route R_DEN_TO_KAN_1 = new Route(4, BLACK , new String[] {DENVER_STRING, KANSASCITY_STRING});
    private final Route R_DEN_TO_KAN_2 = new Route(4, ORANGE , new String[] {DENVER_STRING, KANSASCITY_STRING});
    private final Route R_DEN_TO_OMA = new Route(4, PURPLE , new String[] {DENVER_STRING, OMAHA_STRING});
    private final Route R_HEL_TO_OMA = new Route(5, RED , new String[] {HELENA_STRING, OMAHA_STRING});
    private final Route R_HEL_TO_DUL = new Route(6, ORANGE , new String[] {HELENA_STRING, DULUTH_STRING});
    private final Route R_WIN_TO_SSM = new Route(6, WILD , new String[] {WINNIPEG_STRING, SAULTSTMARIE_STRING});
    private final Route R_WIN_TO_DUL = new Route(4, BLACK , new String[] {WINNIPEG_STRING, DULUTH_STRING});
    private final Route R_HOU_TO_DAL_1 = new Route(1, WILD , new String[] {HOUSTON_STRING, DALLAS_STRING});
    private final Route R_HOU_TO_DAL_2 = new Route(1, WILD , new String[] {HOUSTON_STRING, DALLAS_STRING});
    private final Route R_DAL_TO_OKL_1 = new Route(2, WILD , new String[] {DALLAS_STRING, OKLAHOMACITY_STRING});
    private final Route R_DAL_TO_OKL_2 = new Route(2, WILD , new String[] {DALLAS_STRING, OKLAHOMACITY_STRING});
    private final Route R_OKL_TO_KAN_1 = new Route(2, WILD , new String[] {OKLAHOMACITY_STRING, KANSASCITY_STRING});
    private final Route R_OKL_TO_KAN_2 = new Route(2, WILD , new String[] {OKLAHOMACITY_STRING, KANSASCITY_STRING});
    private final Route R_KAN_TO_OMA_1 = new Route(1, WILD , new String[] {KANSASCITY_STRING, OMAHA_STRING});
    private final Route R_KAN_TO_OMA_2 = new Route(1, WILD , new String[] {KANSASCITY_STRING, OMAHA_STRING});
    private final Route R_OMA_TO_DUL_1 = new Route(2, WILD , new String[] {OMAHA_STRING, DULUTH_STRING});
    private final Route R_OMA_TO_DUL_2 = new Route(2, WILD , new String[] {OMAHA_STRING, DULUTH_STRING});
    private final Route R_DUL_TO_SSM = new Route(3, WILD , new String[] {DULUTH_STRING, SAULTSTMARIE_STRING});
    private final Route R_HOU_TO_ORI = new Route(2, WILD , new String[] {HOUSTON_STRING, NEWORLEANS_STRING});
    private final Route R_DAL_TO_LIT = new Route(2, WILD , new String[] {DALLAS_STRING, LITTLEROCK_STRING});
    private final Route R_OKL_TO_LIT = new Route(2, WILD , new String[] {OKLAHOMACITY_STRING, LITTLEROCK_STRING});
    private final Route R_KAN_TO_SAI_1 = new Route(2, BLUE , new String[] {KANSASCITY_STRING, SAINTLOUIS_STRING});
    private final Route R_KAN_TO_SAI_2 = new Route(2, PURPLE , new String[] {KANSASCITY_STRING, SAINTLOUIS_STRING});
    private final Route R_OMA_TO_CHI = new Route(4, BLUE , new String[] {OMAHA_STRING, CHICAGO_STRING});
    private final Route R_DUL_TO_CHI = new Route(3, RED , new String[] {DULUTH_STRING, CHICAGO_STRING});
    private final Route R_DUL_TO_TOR = new Route(6, PURPLE , new String[] {DULUTH_STRING, TORONTO_STRING});
    private final Route R_SSM_TO_TOR = new Route(2, WILD , new String[] {SAULTSTMARIE_STRING, TORONTO_STRING});
    private final Route R_SSM_TO_MON = new Route(5, BLACK , new String[] {SAULTSTMARIE_STRING, MONTREAL_STRING});
    private final Route R_ORI_TO_LIT = new Route(3, GREEN , new String[] {NEWORLEANS_STRING, LITTLEROCK_STRING});
    private final Route R_LIT_TO_SAI = new Route(2, WILD , new String[] {LITTLEROCK_STRING, SAINTLOUIS_STRING});
    private final Route R_SAI_TO_CHI_1 = new Route(2, WHITE , new String[] {SAINTLOUIS_STRING, CHICAGO_STRING});
    private final Route R_SAI_TO_CHI_2 = new Route(2, GREEN , new String[] {SAINTLOUIS_STRING, CHICAGO_STRING});
    private final Route R_CHI_TO_TOR = new Route(4, WHITE , new String[] {CHICAGO_STRING, TORONTO_STRING});
    private final Route R_TOR_TO_MON = new Route(3, WILD , new String[] {TORONTO_STRING, MONTREAL_STRING});
    private final Route R_ORI_TO_MIA = new Route(6, RED , new String[] {NEWORLEANS_STRING, MIAMI_STRING});
    private final Route R_ORI_TO_ATL_1 = new Route(4, YELLOW , new String[] {NEWORLEANS_STRING, ATLANTA_STRING});
    private final Route R_ORI_TO_ATL_2 = new Route(4, ORANGE , new String[] {NEWORLEANS_STRING, ATLANTA_STRING});
    private final Route R_LIT_TO_NAS = new Route(3, WHITE , new String[] {LITTLEROCK_STRING, NASHVILLE_STRING});
    private final Route R_SAI_TO_NAS = new Route(2, WILD , new String[] {SAINTLOUIS_STRING, NASHVILLE_STRING});
    private final Route R_SAI_TO_PIT = new Route(5, GREEN , new String[] {SAINTLOUIS_STRING, PITTSBURGH_STRING});
    private final Route R_CHI_TO_PIT_1 = new Route(3, BLACK , new String[] {CHICAGO_STRING, PITTSBURGH_STRING});
    private final Route R_CHI_TO_PIT_2 = new Route(3, ORANGE , new String[] {CHICAGO_STRING, PITTSBURGH_STRING});
    private final Route R_MIA_TO_ATL = new Route(5, BLUE , new String[] {MIAMI_STRING, ATLANTA_STRING});
    private final Route R_MIA_TO_CHA = new Route(4, PURPLE , new String[] {MIAMI_STRING, CHARLESTON_STRING});
    private final Route R_ATL_TO_CHA = new Route(2, WILD , new String[] {ATLANTA_STRING, CHARLESTON_STRING});
    private final Route R_ATL_TO_NAS = new Route(1, WILD , new String[] {ATLANTA_STRING, NASHVILLE_STRING});
    private final Route R_ATL_TO_RAL_1 = new Route(2, WILD , new String[] {ATLANTA_STRING, RALEIGH_STRING});
    private final Route R_ATL_TO_RAL_2 = new Route(2, WILD , new String[] {ATLANTA_STRING, RALEIGH_STRING});
    private final Route R_NAS_TO_RAL = new Route(3, BLACK , new String[] {NASHVILLE_STRING, RALEIGH_STRING});
    private final Route R_NAS_TO_PIT = new Route(4, YELLOW , new String[] {NASHVILLE_STRING, PITTSBURGH_STRING});
    private final Route R_CHA_TO_RAL = new Route(2, WILD , new String[] {CHARLESTON_STRING, RALEIGH_STRING});
    private final Route R_RAL_TO_WAS_1 = new Route(2, WILD , new String[] {RALEIGH_STRING, WASHINGTON_STRING});
    private final Route R_RAL_TO_WAS_2 = new Route(2, WILD , new String[] {RALEIGH_STRING, WASHINGTON_STRING});
    private final Route R_RAL_TO_PIT = new Route(2, WILD , new String[] {RALEIGH_STRING, PITTSBURGH_STRING});
    private final Route R_WAS_TO_PIT = new Route(2, WILD , new String[] {WASHINGTON_STRING, PITTSBURGH_STRING});
    private final Route R_WAS_TO_NYC_1 = new Route(2, BLACK , new String[] {WASHINGTON_STRING, NEWYORK_STRING});
    private final Route R_WAS_TO_NYC_2 = new Route(2, ORANGE , new String[] {WASHINGTON_STRING, NEWYORK_STRING});
    private final Route R_PIT_TO_NYC_1 = new Route(2, WHITE , new String[] {PITTSBURGH_STRING, NEWYORK_STRING});
    private final Route R_PIT_TO_NYC_2 = new Route(2, GREEN , new String[] {PITTSBURGH_STRING, NEWYORK_STRING});
    private final Route R_PIT_TO_TOR = new Route(2, WILD , new String[] {PITTSBURGH_STRING, TORONTO_STRING});
    private final Route R_NYC_TO_MON = new Route(3, BLUE , new String[] {NEWYORK_STRING, MONTREAL_STRING});
    private final Route R_NYC_TO_BOS_1 = new Route(2, RED , new String[] {NEWYORK_STRING, BOSTON_STRING});
    private final Route R_NYC_TO_BOS_2 = new Route(2, YELLOW , new String[] {NEWYORK_STRING, BOSTON_STRING});
    private final Route R_MON_TO_BOS_1 = new Route(2, WILD , new String[] {MONTREAL_STRING, BOSTON_STRING});
    private final Route R_MON_TO_BOS_2 = new Route(2, WILD , new String[] {MONTREAL_STRING, BOSTON_STRING});


    private final DestinationCard DEN_TO_EL = new DestinationCard(new String[] {DENVER_STRING, ELPASO_STRING}, 4);
    private final DestinationCard KAN_TO_HOU = new DestinationCard(new String[] {KANSASCITY_STRING, HOUSTON_STRING}, 5);
    private final DestinationCard NYC_TO_ATL = new DestinationCard(new String[] {NEWYORK_STRING, ATLANTA_STRING}, 6);
    private final DestinationCard CHI_TO_ORL = new DestinationCard(new String[] {CHICAGO_STRING, SALTLAKECITY_STRING}, 7);
    private final DestinationCard CAL_TO_SLC = new DestinationCard(new String[] {CALGARY_STRING, SALTLAKECITY_STRING}, 7);
    private final DestinationCard HEL_TO_LA = new DestinationCard(new String[] {HELENA_STRING, LOSANGELES_STRING}, 8);
    private final DestinationCard DUL_TO_HOU = new DestinationCard(new String[] {DULUTH_STRING, HOUSTON_STRING}, 8);
    private final DestinationCard SSM_TO_NAS = new DestinationCard(new String[] {SAULTSTMARIE_STRING, NASHVILLE_STRING}, 8);
    private final DestinationCard MON_TO_ATL = new DestinationCard(new String[] {MONTREAL_STRING, ATLANTA_STRING}, 9);
    private final DestinationCard SSM_TO_OKL = new DestinationCard(new String[] {SAULTSTMARIE_STRING, OKLAHOMACITY_STRING}, 9);
    private final DestinationCard SEA_TO_LA = new DestinationCard(new String[] {SEATTLE_STRING, LOSANGELES_STRING}, 9);
    private final DestinationCard CHI_TO_SAN = new DestinationCard(new String[] {CHICAGO_STRING, SANTAFE_STRING}, 9);
    private final DestinationCard DUL_TO_EL = new DestinationCard(new String[] {DULUTH_STRING, ELPASO_STRING}, 10);
    private final DestinationCard TOR_TO_MIA = new DestinationCard(new String[] {TORONTO_STRING, MIAMI_STRING}, 10);
    private final DestinationCard POR_TO_PHO = new DestinationCard(new String[] {PORTLAND_STRING, PHOENIX_STRING}, 11);
    private final DestinationCard DAL_TO_NYC = new DestinationCard(new String[] {DALLAS_STRING, NEWYORK_STRING}, 11);
    private final DestinationCard DEN_TO_PIT = new DestinationCard(new String[] {DENVER_STRING, PITTSBURGH_STRING}, 11);
    private final DestinationCard WIN_TO_LIT = new DestinationCard(new String[] {WINNIPEG_STRING, LITTLEROCK_STRING}, 11);
    private final DestinationCard WIN_TO_HOU = new DestinationCard(new String[] {WINNIPEG_STRING, HOUSTON_STRING}, 12);
    private final DestinationCard VAN_TO_SAN = new DestinationCard(new String[] {VANCOUVER_STRING, SANTAFE_STRING}, 13);
    private final DestinationCard CAL_TO_PHO = new DestinationCard(new String[] {CALGARY_STRING, PHOENIX_STRING}, 13);
    private final DestinationCard MON_TO_ORL = new DestinationCard(new String[] {MONTREAL_STRING, NEWORLEANS_STRING}, 13);
    private final DestinationCard LA_TO_CHI = new DestinationCard(new String[] {LOSANGELES_STRING, CHICAGO_STRING}, 16);
    private final DestinationCard SAN_TO_ATL = new DestinationCard(new String[] {SANTAFE_STRING, ATLANTA_STRING}, 17);
    private final DestinationCard POR_TO_NAS = new DestinationCard(new String[] {PORTLAND_STRING, NASHVILLE_STRING}, 17);
    private final DestinationCard VAN_NO_MON = new DestinationCard(new String[] {VANCOUVER_STRING, MONTREAL_STRING}, 20);
    private final DestinationCard LA_TO_MIA = new DestinationCard(new String[] {LOSANGELES_STRING, MIAMI_STRING}, 20);
    private final DestinationCard LA_TO_NYC = new DestinationCard(new String[] {LOSANGELES_STRING, NEWYORK_STRING}, 21);
    private final DestinationCard SEA_TO_NYC = new DestinationCard(new String[] {SEATTLE_STRING, NEWYORK_STRING}, 22);
    private final DestinationCard BOS_TO_MIA = new DestinationCard(new String[] {BOSTON_STRING, MIAMI_STRING}, 12);

    private final int COLORED_TICKET_STARTING_COUNT = 12;
    private final int WILD_TICKET_STARTING_COUNT = 14;
    public final int TRAIN_STARTING_COUNT = 48;

    private static TTR_Constants singleton;
    
    private TTR_Constants () {

    }
    
    public static TTR_Constants getInstance() {
        if (singleton == null) {
            singleton = new TTR_Constants();
        }
        
        return  singleton;
    }
    public HashMap<Integer, Integer> getStartingTicketDeck(){
        HashMap<Integer, Integer> newDeck = new HashMap<>();

        newDeck.put(GREEN, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(RED, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(YELLOW, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(BLACK, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(BLUE, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(ORANGE, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(PURPLE, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(WHITE, COLORED_TICKET_STARTING_COUNT);
        newDeck.put(WILD, WILD_TICKET_STARTING_COUNT);

        return newDeck;
    }



    public ArrayList<DestinationCard> getStartingDestinationDeck(){
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

    public Set<Route> getStartingRouteSet(){
        HashSet<Route> newDeck = new HashSet<>();
            newDeck.add(R_VAN_TO_SEA_1);
            newDeck.add(R_VAN_TO_SEA_2);
            newDeck.add(R_SEA_TO_POR_1);
            newDeck.add(R_SEA_TO_POR_2);
            newDeck.add(R_POR_TO_SAN_1);
            newDeck.add(R_POR_TO_SAN_2);
            newDeck.add(R_SAN_TO_SLC_1);
            newDeck.add(R_SAN_TO_SLC_2);
            newDeck.add(R_SAN_TO_LA_1);
            newDeck.add(R_SAN_TO_LA_2);
            newDeck.add(R_VAN_TO_CAL);
            newDeck.add(R_SEA_TO_CAL);
            newDeck.add(R_SEA_TO_HEL);
            newDeck.add(R_POR_TO_SLC);
            newDeck.add(R_LA_TO_LAS);
            newDeck.add(R_LA_TO_PHO);
            newDeck.add(R_LA_TO_EL);
            newDeck.add(R_CAL_TO_WIN);
            newDeck.add(R_CAL_TO_HEL);
            newDeck.add(R_SLC_TO_HEL);
            newDeck.add(R_SLC_TO_DEN_1);
            newDeck.add(R_SLC_TO_DEN_2);
            newDeck.add(R_LAS_TO_SLC);
            newDeck.add(R_PHO_TO_EL);
            newDeck.add(R_PHO_TO_FE);
            newDeck.add(R_PHO_TO_DEN);
            newDeck.add(R_EL_TO_FE);
            newDeck.add(R_FE_TO_DEN);
            newDeck.add(R_DEN_TO_HEL);
            newDeck.add(R_HEL_TO_WIN);
            newDeck.add(R_EL_TO_HOU);
            newDeck.add(R_EL_TO_DAL);
            newDeck.add(R_EL_TO_OKL);
            newDeck.add(R_FE_TO_OKL);
            newDeck.add(R_DEN_TO_OKL);
            newDeck.add(R_DEN_TO_KAN_1);
            newDeck.add(R_DEN_TO_KAN_2);
            newDeck.add(R_DEN_TO_OMA);
            newDeck.add(R_HEL_TO_OMA);
            newDeck.add(R_HEL_TO_DUL);
            newDeck.add(R_WIN_TO_SSM);
            newDeck.add(R_WIN_TO_DUL);
            newDeck.add(R_HOU_TO_DAL_1);
            newDeck.add(R_HOU_TO_DAL_2);
            newDeck.add(R_DAL_TO_OKL_1);
            newDeck.add(R_DAL_TO_OKL_2);
            newDeck.add(R_OKL_TO_KAN_1);
            newDeck.add(R_OKL_TO_KAN_2);
            newDeck.add(R_KAN_TO_OMA_1);
            newDeck.add(R_KAN_TO_OMA_2);
            newDeck.add(R_OMA_TO_DUL_1);
            newDeck.add(R_OMA_TO_DUL_2);
            newDeck.add(R_DUL_TO_SSM);
            newDeck.add(R_HOU_TO_ORI);
            newDeck.add(R_DAL_TO_LIT);
            newDeck.add(R_OKL_TO_LIT);
            newDeck.add(R_KAN_TO_SAI_1);
            newDeck.add(R_KAN_TO_SAI_2);
            newDeck.add(R_OMA_TO_CHI);
            newDeck.add(R_DUL_TO_CHI);
            newDeck.add(R_DUL_TO_TOR);
            newDeck.add(R_SSM_TO_TOR);
            newDeck.add(R_SSM_TO_MON);
            newDeck.add(R_ORI_TO_LIT);
            newDeck.add(R_LIT_TO_SAI);
            newDeck.add(R_SAI_TO_CHI_1);
            newDeck.add(R_SAI_TO_CHI_2);
            newDeck.add(R_CHI_TO_TOR);
            newDeck.add(R_TOR_TO_MON);
            newDeck.add(R_ORI_TO_MIA);
            newDeck.add(R_ORI_TO_ATL_1);
            newDeck.add(R_ORI_TO_ATL_2);
            newDeck.add(R_LIT_TO_NAS);
            newDeck.add(R_SAI_TO_NAS);
            newDeck.add(R_SAI_TO_PIT);
            newDeck.add(R_CHI_TO_PIT_1);
            newDeck.add(R_CHI_TO_PIT_2);
            newDeck.add(R_MIA_TO_ATL);
            newDeck.add(R_MIA_TO_CHA);
            newDeck.add(R_ATL_TO_CHA);
            newDeck.add(R_ATL_TO_NAS);
            newDeck.add(R_ATL_TO_RAL_1);
            newDeck.add(R_ATL_TO_RAL_2);
            newDeck.add(R_NAS_TO_RAL);
            newDeck.add(R_NAS_TO_PIT);
            newDeck.add(R_CHA_TO_RAL);
            newDeck.add(R_RAL_TO_WAS_1);
            newDeck.add(R_RAL_TO_WAS_2);
            newDeck.add(R_RAL_TO_PIT);
            newDeck.add(R_WAS_TO_PIT);
            newDeck.add(R_WAS_TO_NYC_1);
            newDeck.add(R_WAS_TO_NYC_2);
            newDeck.add(R_PIT_TO_NYC_1);
            newDeck.add(R_PIT_TO_NYC_2);
            newDeck.add(R_PIT_TO_TOR);
            newDeck.add(R_NYC_TO_MON);
            newDeck.add(R_NYC_TO_BOS_1);
            newDeck.add(R_NYC_TO_BOS_2);
            newDeck.add(R_MON_TO_BOS_1);
            newDeck.add(R_MON_TO_BOS_2);

        return newDeck;
    }

    public Route getRoute(String firstLocation, String secondLocation) {
        Set<Route> routes = getStartingRouteSet();
        for (Route r: routes) {
            if (r.getLocation()[0].equals(firstLocation) && r.getLocation()[1].equals(secondLocation)) {
                return r;
            }
        }
        return null;
    }

    public TrainCard getTicket(int colorNumber) {
        switch (colorNumber) {
            case (0): return new TrainCard(1);
            case (1): return new TrainCard(2);
            case (2): return new TrainCard(3);
            case (3): return new TrainCard(4);
            case (4): return new TrainCard(5);
            case (5): return new TrainCard(6);
            case (6): return new TrainCard(7);
            case (7): return new TrainCard(8);
            case (8): return new TrainCard(9);
            default: return null;
        }
    }
}
