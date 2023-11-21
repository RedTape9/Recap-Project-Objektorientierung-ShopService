import lombok.With;

import java.time.LocalDateTime;
import java.util.List;
@With
public record Order(
        OrderStatus status,
        String id,
        List<Product> products,
        LocalDateTime timestamp
) {

}
