sleep_data_counter(0).
trail_data_counter(0).

!start.

+!start : true <-
    .print("Hello world").    

+available_container(ContainerName) : true <-
    !!monitor_sleep;
    !!monitor_training.

+!monitor_sleep : sleep_data_counter(Counter) <-
    .random([5,6.5,7], HoursOfSleep);
    .print("Monitored hours of sleep for day ", Counter, ": ", HoursOfSleep);
    !publish_sleep_data(HoursOfSleep, Counter);
    .wait(4000);
    -+sleep_data_counter(Counter+1);
    !!monitor_sleep.

+!monitor_training : trail_data_counter(Counter) <-
    .random([3,5.5], TrailKilometers);
    .print("Monitored kilometers of training for day ", Counter, ": ", TrailKilometers);
    !publish_trail_data(TrailKilometers, Counter);
    .wait(4000);
    -+trail_data_counter(Counter+1);
    !!monitor_training.

+!publish_sleep_data(HoursOfSleep, Index) : Index>0 & available_container(ContainerName) <-
    .print("Publishing hours of sleep");
    updateData(ContainerName, "sleep.txt", [HoursOfSleep]).

+!publish_sleep_data(HoursOfSleep, Index) : available_container(ContainerName) <-
    .print("Publishing hours of sleep");
    publishData(ContainerName, "sleep.txt", [HoursOfSleep]).

+!publish_trail_data(TrailKilometers, Index) : Index>0 & available_container(ContainerName) <-
    .print("Publishing trail kilometers");
    updateData(ContainerName, "trail.txt", [TrailKilometers]).

+!publish_trail_data(TrailKilometers, Index) : available_container(ContainerName) <-
    .print("Publishing trail kilometers");
    publishData(ContainerName, "trail.txt", [TrailKilometers]).

/* Import behavior of agents that work in CArtAgO environments */
{ include("$jacamoJar/templates/common-cartago.asl") }