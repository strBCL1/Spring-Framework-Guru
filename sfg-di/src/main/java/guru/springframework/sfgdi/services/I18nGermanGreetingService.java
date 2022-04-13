package guru.springframework.sfgdi.services;

public class I18nGermanGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hi Welte in German!";
    }
}
