package Organization;



import Exceptions.IncorrectValueException;
import ParceFile.LocalDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
@XmlRootElement(name = "organization")
public class Organization {
        @XmlElement(name = "id", required = true)
        private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        @XmlElement(name = "name", required = true)
        private String name; //Поле не может быть null, Строка не может быть пустой
        @XmlElement(name = "coordinates", required = true)
        private Coordinates coordinates; //Поле не может быть null

        private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        @XmlElement(name = "annualTurnover", required = true)
        private Float annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
        @XmlElement(name = "fullName", required = true)
        private String fullName; //Поле может быть null
        @XmlElement(name = "type", required = true)
        private OrganizationType type; //Поле не может быть null
        @XmlElement(name = "officialAddress", required = true)
        private Address officialAddress; //Поле не может быть null


        public Organization(Long id, String name,Coordinates coordinates,java.time.LocalDate creationDate, Float annualTurnover,
                            String fullName, OrganizationType type, Address officialAddress) {
            this.id = id;
            this.name = name;
            this.coordinates = coordinates;
            this.creationDate = creationDate;
            this.annualTurnover = annualTurnover;
            this.fullName = fullName;
            this.officialAddress = officialAddress;
            this.type = type;
        }

        public Organization(){

        }


        @XmlTransient
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            if(id==null || id<=0){
                throw new IllegalArgumentException("Id must be greater than 0 and cannot be null");
            }
            this.id = id;
        }

        @XmlTransient
        public String getName() {
            return name;
        }

        public void setName(String name) throws IncorrectValueException {
            if(name == null || name.isEmpty()){
                throw new IncorrectValueException("Name cannot be null or empty");
            }
            this.name = name;
        }

        @XmlTransient
        public Coordinates getCoordinates() {
            return coordinates;
        }


        public void setCoordinates(Coordinates coordinates) throws IncorrectValueException {
            if(coordinates == null){
                throw new IncorrectValueException("Coordinates cannot be null");
            }
            this.coordinates = coordinates;
        }

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        public LocalDate getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(LocalDate creationDate) throws IncorrectValueException {
            if(creationDate == null){
                throw new IncorrectValueException("Creation date cannot be null");
            }
            this.creationDate = creationDate;
        }

        @XmlTransient
        public Float getAnnualTurnover() {
            return annualTurnover;
        }

        public void setAnnualTurnover(Float annualTurnover) throws IncorrectValueException {
            if(annualTurnover<=0){
                throw new IncorrectValueException("Annual turnover must be grater than 0");
            }
            this.annualTurnover = annualTurnover;
        }

        @XmlTransient
        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) throws IncorrectValueException {
            if(fullName!=null && fullName.isEmpty()){
                throw new IncorrectValueException("Full name cannot be an empty string");
            }
            this.fullName = fullName;
        }

        @XmlTransient
        public OrganizationType getType() {
            return type;
        }

        public void setType(OrganizationType type) {
            this.type = type;
        }

        @XmlTransient
        public Address getOfficialAddress() {
            return officialAddress;
        }

        public void setOfficialAddress(Address officialAddress) {

            this.officialAddress = officialAddress;
        }

        @Override
        public String toString() {
            return "Organization{" +
                    "id=" + id +
                    " name=" + name + '\n' +
                    " coordinates=" + coordinates + '\n' +
                    " creationDate=" + creationDate + '\n' +
                    " annualTurnover=" + annualTurnover + '\n' +
                    " fullName=" + fullName + '\n' +
                    " type=" + type + '\n' +
                    " officialAddress=" + officialAddress +
                    '}'+'\n'+'\n';
        }

}
