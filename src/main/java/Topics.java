enum Topics {

  MY_TOPIC("my-topic");

  private String topic;

  Topics(String topic) {
    this.topic = topic;
  }

  public String getTopic() {
    return topic;
  }
}