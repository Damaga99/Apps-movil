package es.imovildani.persistencia;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.imovildani.persistencia.databinding.ItemViewBinding;

public class Adapter extends RecyclerView.Adapter {


    BookViewModel bookViewModel;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemViewBinding binding;
        private BookViewModel BookViewModel;

        public ViewHolder(@NonNull ItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        private void bind(Book book) {
            binding.title.setText(book.getTitle());
// Completar para el resto de elementos
            binding.author.setText(book.getAuthor());
            binding.editor.setText(book.getEditorial());
            binding.isbn.setText(book.getIsbn());
            binding.price.setText(String.valueOf(book.getPrice()));
        }

        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            binding = ItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

            return new ViewHolder(binding);
        }

        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(BookViewModel.getBook(position));

        }

        public int getItemCount() {
            if (BookViewModel != null)
                return BookViewModel.getListSize();
            return 0;
        }

    }

    public void setBookViewModel(BookViewModel bookViewModel){
        this.bookViewModel = bookViewModel;
        notifyDataSetChanged();
    }

}


