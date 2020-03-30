package ye.studentrest;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String gender;
    private String birthday;
    private String native_place;
    private String major;
    
    Student()
    {
      
    }

    public Student(String name, String gender, String birthday, String native_place, String major)
    {
      this.name=name;
      this.gender=gender;
      this.birthday=birthday;
      this.native_place=native_place;
      this.major=major;
    }
    
    public Long getId() {
		return this.id;
    }
    
    public String getName() {
		return this.name;
    }

    public String getGender() {
		return this.gender;
    }

    public String getBirthday() {
		return this.birthday;
    }

    public String getNative_place() {
		return this.native_place;
    }

    public String getMajor() {
		return this.major;
    }

    public void setId(Long id) {
      this.id=id;
    }

    public void setName(String name) {
      this.name=name;
    }

    public void setGender(String gender) {
      this.gender=gender;
    }

    public void setBirthday(String birthday) {
      this.birthday=birthday;
    }

    public void setNative_place(String native_place) {
      this.native_place=native_place;
    }

    public void setMajor(String major) {
      this.major=major;
    }

    @Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("name", this.getName()).append("gender", this.getGender())
				.append("birthday", this.getBirthday()).append("native_place", this.getNative_place()).append("major", this.getMajor())
				.toString();
	}
  


}