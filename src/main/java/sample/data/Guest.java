package sample.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Guest {

    private String name;
    private String food;
    private String drink;
    private String phone;

    @Override
    public String toString() {
        return name;
    }
}
