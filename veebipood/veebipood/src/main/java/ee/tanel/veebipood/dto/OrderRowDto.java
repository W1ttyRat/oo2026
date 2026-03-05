package ee.tanel.veebipood.dto;

public record OrderRowDto( // DTO -> data transfer object
        Long productId,
        int quantity
) {
}
