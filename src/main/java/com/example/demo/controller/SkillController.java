@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    SkillService service;

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return service.createSkill(skill);
    }

    @GetMapping
    public List<Skill> fetchAllSkills() {
        return service.fetchAllSkills();
    }

    @GetMapping("/{id}")
    public Optional<Skill> fetchSkillById(@PathVariable int id) {
        return service.fetchSkillById(id);
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateSkill(@PathVariable int id) {
        Optional<Skill> skill = service.fetchSkillById(id);

        if (skill.isPresent()) {
            service.deactivateSkill(id);
            return "Skill Deactivated Successfully";
        } else {
            return id + " not found";
        }
    }
}
