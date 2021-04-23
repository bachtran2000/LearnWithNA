package TCP_Example;

public class sinhvien {
    protected String id, name, date, add,score;

    public sinhvien(String id, String name, String date, String add, String score) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.add = add;
        this.score = score;
    }

    public sinhvien() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return id +"$"+ name + "$"+ date + "$" + add + "$" + score ;
    }
    public String toShow(){
        return "Id: "+id +" Name: "+ name + " Date: "+ date + " Add: " + add + " Diem: " + score ;
    }
}
