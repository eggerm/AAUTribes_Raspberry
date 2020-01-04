package aau_tribes;

public class MovementResponse {

    private String answerToSend;
    private int objectId;
    private int objectType;

    public MovementResponse(String answerToSend, int objectId, int objectType) {
        this.answerToSend = answerToSend;
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public String getAnswerToSend() {
        return answerToSend;
    }

    public int getObjectId() {
        return objectId;
    }

    public int getObjectType() {
        return objectType;
    }
}
