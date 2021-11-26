package com.example.felece_ticket.Services;

import com.example.felece_ticket.models.Cities;
import com.example.felece_ticket.models.Train;
import com.example.felece_ticket.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class CitiesService {
    private final CitiesRepository citiesRepository;

    @Autowired
    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public List<Cities> getCities() {
        return citiesRepository.findAll();
    }

    public String add_city(Cities city) {
        try {
            citiesRepository.save(city);
            return "Şehir başarıyla eklendi";
        } catch (Exception e) {
            return "Şehir eklenemedi    " + e;
        }
    }

    public Cities findByCityId(String city_id){
        return citiesRepository.findByCityId(city_id);
    }

    public String delete(Cities city){
        try {
            citiesRepository.delete(city);
            return "Silme işlemi Başarılı";
        }
        catch (Exception e){
            return "Tren silinemedi!    "+e;
        }
    }

    public String Update(Cities city,String city_id,String city_name) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(city.getId()));
            city.setCity_id(city_id);
            city.setCity_name(city_name);


            citiesRepository.save(city);
            return "Güncelleme Başarılı";
        }
        catch (Exception exception){
            return "Güncelleme Hatalı   "+exception;
        }
    }


}
