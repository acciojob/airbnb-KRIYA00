package com.driver;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Repository
public class HotelManagementRepository
{
    private HashMap<String, Hotel> hotelDb = new HashMap<>();

    private HashMap<Integer, User> userDb = new HashMap<>();

    private HashMap<String, Booking> bookingDb = new HashMap<>();
    private HashMap<Integer, List<String>> UserbookingDb = new HashMap<>();


    public String addHotel(Hotel hotel){

        if(hotel==null || hotel.getHotelName()==null)
        {
            return "FAILURE";
        }

        if(hotelDb.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        hotelDb.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }
    public Integer addUser(User user){


        userDb.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }


    public String getHotelWithMostFacilities()
    {
        int max=0;
        String ans="";
        for(String hotelname :hotelDb.keySet())
        {
            Hotel h=hotelDb.get(hotelname);
            int count=h.getFacilities().size();
            if(count>max)
            {
                max=count;
                ans=hotelname;

            }
            if(count==max)
            {
                ans=ans.compareTo(hotelname)>=0?ans:hotelname;
            }
        }
        return ans;
    }



    public int bookARoom(Booking booking)
    {
     String bookingid=UUID.randomUUID().toString();
     Hotel h=hotelDb.get(booking.getHotelName());
     int availablerooms=h.getAvailableRooms();
     int requiredrooms=booking.getNoOfRooms();
     int totalprice=-1;
     if(availablerooms>=requiredrooms)
     {
         h.setAvailableRooms(availablerooms-requiredrooms);
         totalprice=h.getPricePerNight()*requiredrooms;
     }
     if(!UserbookingDb.containsKey(booking.getBookingAadharCard()))
     {
         UserbookingDb.put(booking.getBookingAadharCard(),new ArrayList<>());
     }
     UserbookingDb.get(booking.getBookingAadharCard()).add(bookingid);
     return totalprice;

    }


    public int getBookings(Integer aadharCard)
    {
        return UserbookingDb.get(aadharCard).size();
    }



    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName)
    {
        Hotel h=hotelDb.get(hotelName);
         for(Facility f:newFacilities )
         {
             if(!h.getFacilities().contains(f))
             {
                 h.getFacilities().add(f);
             }
         }
         hotelDb.put(hotelName,h);
         return h;

    }



}
