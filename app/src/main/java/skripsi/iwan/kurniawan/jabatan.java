package skripsi.iwan.kurniawan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class jabatan extends RecyclerView.Adapter<jabatan.ViewHolder>{

    private Context context;
    private List<UserJabatan> jabatan;
    // Declare Interface
    private static ClickListener clickListener;
    public jabatan(Context context, List<UserJabatan> jabatan) {
        this.jabatan = jabatan;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_user_jabatan, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public int getItemCount() {
        return jabatan.size();
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserJabatan userJabatan = jabatan.get(position);

        holder.jabatan.setText(userJabatan.getJabatan());
        holder.gp.setText(userJabatan.getGp());

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView jabatan, gp;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


            jabatan = (TextView) itemView.findViewById(R.id.text1);
            gp = (TextView) itemView.findViewById(R.id.text2);

        }
        // Untuk menghandle event onclick
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
        // Untuk menghandle event onlongclick
        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        skripsi.iwan.kurniawan.jabatan.clickListener = clickListener;
    }
    // Interface
    public interface ClickListener {
        void onItemClick(final int position, View v);
        void onItemLongClick(int position, View v);
    }
}