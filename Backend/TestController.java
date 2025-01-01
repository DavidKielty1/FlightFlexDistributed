@RestController
public class TestController {

    @Autowired
    private AdRecommendationRepository repository;

    @GetMapping("/test")
    public List<AdRecommendation> getRecommendations() {
        return repository.findAll();
    }
}
