package sample.model;

public class SequencedActionTrigger {

    //All Timings are given in relation to the Timing of another SequencedActionTrigger

    //TiminType determines how the Timings can be changed, eg is the Beginning or the Duration affect by changes in gamespeed


    private int startTimingType; //Todo maybe turn into Class??
    private int endTimingType;

    private SequencedActionTriggerTiming timingData;  //Dont Serialize
    private int timingDataId;

    //Todo Actions





}
