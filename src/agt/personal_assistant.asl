!start.

+!start : true <-
    .print("Hello world");
    !createPersonalDataContainer;
    !suggest_activity.

+!createPersonalDataContainer : true <-
    .print("Creating personal data container");
    createContainer("personal-data");
    .broadcast(tell, available_container("personal-data")).

+!suggest_activity : true <-
    .wait(10000);
    readData("personal-data", "watchlist.txt", Movies);
    .print("Read watchlist data");
    readData("personal-data", "trail.txt", TrailKilometers);
    .print("Read training data");
    readData("personal-data", "sleep.txt", HoursOfSleep);
    .length(HoursOfSleep, SleepLogsNum);
    .nth(SleepLogsNum-1, HoursOfSleep, LastSleepLog);
    .print("Read hours of sleep of last night: ", LastSleepLog);
    !suggest_activity(LastSleepLog, TrailKilometers, Movies);
    !suggest_activity.

+!suggest_activity(LastSleepLog, TrailKilometers, Movies) : LastSleepLog >= 7 <-
    .max(TrailKilometers, SuggestedTrail);
    .print("Suggesting known trail of kilometers: ", SuggestedTrail).

+!suggest_activity(LastSleepLog, TrailKilometers, Movies) : LastSleepLog <= 5 <-
    .nth(0, Movies, SuggestedMovie);
    .print("Suggesting movie: ", SuggestedMovie).

+!suggest_activity(LastSleepLog, TrailKilometers, Movies) : true <-
    .min(TrailKilometers, SuggestedTrail);
    .print("Suggesting known trail of kilometers: ", SuggestedTrail).

/* Import behavior of agents that work in CArtAgO environments */
{ include("$jacamoJar/templates/common-cartago.asl") }