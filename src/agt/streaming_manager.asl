watchlist(["Matrix", "Inseption", "Avengers: Endgame"]).

+!start : true <-
    .print("Hello world").    

+available_container(ContainerName) : true <-
    !publish_watchlist.

+!publish_watchlist : watchlist(Movies) <-
    .print("Publishing movies from watchlist: ", Movies);
    publishData("personal-data", "watchlist.txt", Movies).

/* Import behavior of agents that work in CArtAgO environments */
{ include("$jacamoJar/templates/common-cartago.asl") }