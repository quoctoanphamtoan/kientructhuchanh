package bookclient.test;

import java.io.IOException;
import java.util.List;

import bookclient.BookService;
import bookclient.helper.RetrofitClientCreator;
import bookclient.model.Book;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class test {
public static void main(String[] args) throws IOException {
	 Retrofit retrofit = RetrofitClientCreator.getClient(); 
	BookService authService = retrofit.create(BookService.class);
	Call<List<Book>> call = authService.getBook();
	Response<List<Book>> response = call.execute(); 
	response.body().forEach(x->{
		System.out.println(x);
	});
}
}
