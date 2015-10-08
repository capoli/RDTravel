package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Stateless
@LocalBean
public class XmlEJB {
    public void exportTrips(TripGroup tripGroup, OutputStream os) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TripGroup.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.marshal(tripGroup, os);
    }

    public List<Trip> importTrips(InputStream is) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TripGroup.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        TripGroup tripGroup = (TripGroup) unmarshaller.unmarshal(is);
        return tripGroup.getTrips();
    }
}
