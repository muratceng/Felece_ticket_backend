package com.example.felece_ticket.Services;


import com.example.felece_ticket.models.Train;
import com.example.felece_ticket.models.User;
import com.example.felece_ticket.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.query.Query;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TrainService {
    private final TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository){
        this.trainRepository=trainRepository;
    }

    public List<Train> getTrains(){ return trainRepository.findAll();}

    public String addTrain(Train train){
        try{
            trainRepository.save(train);
            return "Tren Eklendi";
        }
        catch (Exception e){
            return "HATA!!  "+e;
        }
    }

    public String new_train(Train train) {
        try {
            String tmp = "";
            for (int i = 1; i < train.getTotal_ticket(); i++) {
                tmp += String.valueOf(i) + ",";
            }
            tmp += train.getTotal_ticket();
            train.setEmpty_seats(tmp);
            trainRepository.save(train);
            return "Tren Başarıyla Eklendi! ";
        }
        catch (Exception e){
            return "Tren Eklenemedi     "+e;
        }
    }
    public List<Train> findByRoute(String departure_id,String arrival_id) {
        try {
            Query query = new Query();

            List<Train> trainList ;
            trainList = (List<Train>) query.addCriteria(Criteria.where("departure_id").is(departure_id).and("arrival_id").is(arrival_id));

            return trainList;
        }
        catch (Exception e){
            return null;
        }
    }

    public Train findbyid(String id){
        return trainRepository.findbyid(id);
    }

    public String delete(Train train){
        try {
            trainRepository.delete(train);
            return "Silme işlemi Başarılı";
        }
        catch (Exception e){
            return "Tren silinemedi!    "+e;
        }
    }

    public String deletebyid(String id){
        try{
            Train train=trainRepository.findbyid(id);
            trainRepository.delete(train);
            return train + " id'li tren silinmiştir! ";
        }
        catch (Exception e){
            return "Hata! tren silinemedi    "+e;
        }
    }

    public String Update(Train train, String departure_id, String arrival_id, String empty_seats, String reserved_seats, String sold_seats, Date date, int total_num) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(train.getId()));
            train.setArrival_id(arrival_id);
            train.setDeparture_id(departure_id);
            train.setEmpty_seats(empty_seats);
            train.setReserved_seats(reserved_seats);
            train.setSold_seats(sold_seats);
            train.setDate(date);
            train.setTotal_ticket(total_num);

            trainRepository.save(train);
            return "Güncelleme Başarılı";
        }
        catch (Exception exception){
            return "Güncelleme Hatalı   "+exception;
        }
    }
    public void updateEmptySeats(Train train,String seat,Boolean decrease){
        String tmp = train.getEmpty_seats();
        List<String> empty =new ArrayList<String>(Arrays.asList(tmp.split(",")));
        if(decrease==true && empty.size()>1)
        {

            empty.remove(seat);
            String seattmp="";
            for(int i =0;i<empty.size();i++){
                seattmp +=empty.get(i)+",";
            }
            train.setEmpty_seats(seattmp);
            trainRepository.save(train);

        }
        else if(decrease==true && empty.size()==1)
        {
            String seattmp="";
            train.setEmpty_seats(seattmp);
            trainRepository.save(train);
        }
        else if (decrease == false) {

            empty.add(seat);
            String seattmp="";
            for(int i =0;i<empty.size();i++){
                seattmp +=empty.get(i)+",";
            }
            train.setEmpty_seats(seattmp);
            trainRepository.save(train);

        }
    }
    public void updateSoldSeats(Train train,String seat,boolean decrease){

        String tmp = train.getSold_seats();
        List<String> empty =new ArrayList<String>(Arrays.asList(tmp.split(",")));
        if(decrease==true && empty.size()>1)
        {
            empty.remove(seat);
            String seattmp="";
            for(int i =0;i<empty.size();i++){
                seattmp +=empty.get(i)+",";
            }
            train.setSold_seats(seattmp);
            trainRepository.save(train);

        }
        else if(decrease==true && empty.size()==1)
        {
            String seattmp="";
            train.setSold_seats(seattmp);
            trainRepository.save(train);
        }
        else
        {
            empty.add(seat);
            String seattmp="";
            for(int i =0;i<empty.size();i++){
                seattmp +=empty.get(i)+",";
            }
            train.setSold_seats(seattmp);
            trainRepository.save(train);

        }

    }
    public void updateReservedSeats(Train train,String seat,boolean decrease){


        String tmp = train.getReserved_seats();
        List<String> empty =new ArrayList<String>(Arrays.asList(tmp.split(",")));
        if(decrease==true && empty.size()>1)
        {

            empty.remove(seat);
            String seattmp="";
            for(int i =0;i<empty.size();i++){
                seattmp +=empty.get(i)+",";
            }
            train.setReserved_seats(seattmp);
            trainRepository.save(train);

        }
        else if(decrease==true && empty.size()==1)
        {
            String seattmp="";
            train.setReserved_seats(seattmp);
            trainRepository.save(train);
        }
        else
        {
            empty.add(seat);
            String seattmp="";
            for(int i =0;i<empty.size();i++){
                seattmp +=empty.get(i)+",";
            }
            train.setReserved_seats(seattmp);
            trainRepository.save(train);

        }
    }



}
