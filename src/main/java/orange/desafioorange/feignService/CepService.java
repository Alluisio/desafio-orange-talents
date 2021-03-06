package orange.desafioorange.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "cep", url = "https://viacep.com.br")
public interface CepService {

    @RequestMapping("/ws/{cep}/json/")
    Cep getCep (@PathVariable("cep")String cep);
}
