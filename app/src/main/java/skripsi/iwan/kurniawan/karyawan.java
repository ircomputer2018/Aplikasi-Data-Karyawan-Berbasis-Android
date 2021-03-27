package skripsi.iwan.kurniawan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class karyawan extends RecyclerView.Adapter<karyawan.ViewHolder>{



    private Context context;
    private List<UserKaryawan> karyawan;
    // Declare Interface
    private static ClickListener clickListener;
    public karyawan(Context context, List<UserKaryawan> karyawan) {
        this.karyawan = karyawan;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_user_karyawan, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public int getItemCount() {
        return karyawan.size();
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserKaryawan userKaryawan = karyawan.get(position);

        holder.nama.setText(userKaryawan.getNama());
        holder.jabatan.setText(userKaryawan.getJabatan());
        holder.email.setText(userKaryawan.getEmail());
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView nama, jabatan, email;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            nama = (TextView) itemView.findViewById(R.id.nama);
            jabatan = (TextView) itemView.findViewById(R.id.jabatan);
            email = (TextView) itemView.findViewById(R.id.email);
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
        skripsi.iwan.kurniawan.karyawan.clickListener = clickListener;
    }
    // Interface
    public interface ClickListener {
        void onItemClick(final int position, View v);
        void onItemLongClick(int position, View v);
    }
}