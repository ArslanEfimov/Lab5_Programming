package Organization;

import java.time.LocalDate;

public class Organization {

        private Long id;
        private String name;
        private Coordinates coordinates;
        private java.time.LocalDate creationDate;
        private Float annualTurnover;
        private String fullName;
        private OrganizationType type;
        private Address officialAddress;


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



        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }

        public LocalDate getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
        }

        public Float getAnnualTurnover() {
            return annualTurnover;
        }

        public void setAnnualTurnover(Float annualTurnover) {
            this.annualTurnover = annualTurnover;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public OrganizationType getType() {
            return type;
        }

        public void setType(OrganizationType type) {
            this.type = type;
        }

        public Address getOfficialAddress() {
            return officialAddress;
        }

        public void setOfficialAddress(Address officialAddress) {
            this.officialAddress = officialAddress;
        }

        @Override
        public String toString() {
            return "        Organization{" +
                    "id=" + id +
                    " name=" + name + '\n' +
                    " coordinates=" + coordinates + '\n' +
                    " creationDate=" + creationDate + '\n' +
                    " annualTurnover=" + annualTurnover + '\n' +
                    " fullName=" + fullName + '\n' +
                    " type=" + type + '\n' +
                    " officialAddress=" + officialAddress +
                    '}'+'\n';
        }

}
