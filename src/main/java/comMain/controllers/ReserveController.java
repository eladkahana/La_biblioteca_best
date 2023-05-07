package comMain.controllers;

import comMain.entities.ReserveEntity;
import comMain.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping
    public String save(@Valid @RequestBody ReserveEntity vO) {
        return reserveService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        reserveService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ReserveEntity vO) {
        reserveService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ReserveEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return reserveService.getById(id);
    }


    @GetMapping("/ReserveDaysLeft")
    public List<Object[]> ReserveDaysLeft(@RequestParam("arrStr") String arrStr){
        return reserveService.ReserveDaysLeft(arrStr);
    }


}
