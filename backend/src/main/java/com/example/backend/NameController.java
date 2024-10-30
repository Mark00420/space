import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NameController {
    @Autowired
    private NameRepository nameRepository;

    @GetMapping("/")
    public String index() {
        return "index";  // This will look for index.html in src/main/resources/templates or static directory
    }

    @PostMapping("/api/names")
    public @ResponseBody Name createName(@RequestBody Name name) {
        return nameRepository.save(name);
    }
}
