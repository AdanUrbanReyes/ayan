public class InmutableClass {

    /**
     * Address is a mutable class; cause anyone can change its state
     */
    static class Address {

        private String streat;
        private Integer externalNumber;
        private Short internalNumber;

        public Address(String streat, Integer externalNumber, Short internalNumber) {
            this.streat = streat;
            this.externalNumber = externalNumber;
            this.internalNumber = internalNumber;
        }

        public Address(Address address) {
            this(address.getStreat()
                    , address.getExternalNumber()
                    , address.getInternalNumber());
        }

        public String getStreat() {
            return streat;
        }

        public void setStreat(String streat) {
            this.streat = streat;
        }

        public Integer getExternalNumber() {
            return externalNumber;
        }

        public void setExternalNumber(Integer externalNumber) {
            this.externalNumber = externalNumber;
        }

        public Short getInternalNumber() {
            return internalNumber;
        }

        public void setInternalNumber(Short internalNumber) {
            this.internalNumber = internalNumber;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "streat='" + streat + '\'' +
                    ", externalNumber=" + externalNumber +
                    ", internalNumber=" + internalNumber +
                    '}';
        }
    }

    /**
     * In order to build a inmutable class you must
     * have to :
     * 1. Declare a class as final; its ensure no one can inheritance from this and the override methods or properties
     * 2. Declare its attributes as final; its ensure that once the attributes are initialized no one can't change their value
     * 3. When the class has mutable attributes (like address in this example), on getters methods you must return a copy of that attribute, this way the will change another object and not the original object
     */
    static final class Person {

        final private String fullName;
        final private Byte age;
        final private Address address;

        public Person(String fullName, Byte age, Address address) {
            this.fullName = fullName;
            this.age = age;
            this.address = address;
        }

        public String getFullName() {
            return fullName;
        }

        public Byte getAge() {
            return age;
        }

        public Address getAddress() {
            return new Address(address);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "fullName='" + fullName + '\'' +
                    ", age=" + age +
                    ", address=" + address +
                    '}';
        }

    }

    public static void main(String... args) {
        Address a = new Address("Allende", 18, (short) 0);
        Person p = new Person("Adan Urban Reyes", (byte) 27, a);
        System.out.println(p);
        p.getAddress().setStreat("Geminis");
        p.getAddress().setExternalNumber(4);
        p.getAddress().setInternalNumber((short) 0);
        System.out.println(p);
    }

}