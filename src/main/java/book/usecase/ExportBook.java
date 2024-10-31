package book.usecase;

public class ExportBook {
    private BookUsecase bookUsecase;

    public ExportBook(BookUsecase bookUsecase) {
        this.bookUsecase = bookUsecase;
    }

    public void execute(String filePath) {
        // Thêm mã để xuất sách ra file
        // Ví dụ: xuất danh sách sách sang file CSV hoặc bất kỳ định dạng nào khác
    }
}

