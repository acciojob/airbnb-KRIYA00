package com.driver;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class HotelManagementService

{
    HotelManagementRepository hrm=new HotelManagementRepository();
    public String addHotel(Hotel hotel){
        return hrm.addHotel(hotel);
    }
    public Integer addUser(User user)
    {
        return hrm.addUser(user);

        }

    public String getHotelWithMostFacilities() {
        return hrm.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return hrm.bookARoom(booking);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hrm.updateFacilities(newFacilities,hotelName);
    }

    public int getBookings(Integer aadharCard) {
        return hrm.getBookings(aadharCard);
    }
}
