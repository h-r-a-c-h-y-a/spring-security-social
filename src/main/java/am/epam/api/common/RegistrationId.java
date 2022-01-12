package am.epam.api.common;

public enum RegistrationId {
    GOOGLE("google"), FACEBOOK("facebook"), GITHUB("github");

    private final String name;

    RegistrationId(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
