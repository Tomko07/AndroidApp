package com.llamadroid.clem.myneighbourhood.models;


public class PostType
{
    public enum Tags
    {
        OneTimeEvent, RecurringEvent,
        Cleaning, Repair, Construction, Masonry,
        Plumbing, Electrical, Gas, Heater, Boiler, KitchenEquipment,
        Chimney, RainGutter, Roof, Garden, SepticSystems,
        Vehicle,
        BabySitting, ElderlySitting, DogSitting, HouseSitting,
        Carpooling,
    }

    public enum Category
    {
        Announcement,
        Borrow,
        Buy,
        Event,
        Found,
        Give,
        Lost,
        Sell,
        ServiceOffer,
        ServiceRequest,
        Question;
    }

}
