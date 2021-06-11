package js.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SearchCommand {
    private String column;
    private String content;
    private String criteria;
}
