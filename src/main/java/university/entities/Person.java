package university.entities;

public abstract class Person {
    private int id;
    private String name;
    private String email;

    public Person(int id, String name, String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Помилка: неправильний формат email.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Помилка: неправильний формат email.");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Ім'я: " + name + ", Email: " + email;
    }
}
