package me.jpomykala.model.tag;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jpomykala.model.tag.view.TagView;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Evelan-E6540 on 29/08/2015.
 */
@Entity
@Getter
@Setter
@Table(name = "TAGS")
@NoArgsConstructor
public class Tag implements Serializable {

    private static final long serialVersionUID = 4165435948876840930L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true)
    @JsonView({
            TagView.ListRow.class,
            TagView.FullView.class})
    private String name;

    @Version
    private Long version;

    public Tag(String stringTag) {
        this.name = stringTag;
    }


    @Override
    public boolean equals(Object object) {
        return object instanceof Tag && this.name.equalsIgnoreCase(((Tag) object).getName());
    }

    @Override
    public int hashCode() {
        return name.length();
    }

}