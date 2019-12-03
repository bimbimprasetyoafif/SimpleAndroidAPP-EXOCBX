package com.dicoding.assosiate.exo_cbxprofile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvLayout;
    private ArrayList<MemberData.Member> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Member List");
        rvLayout = findViewById(R.id.RecyclerViewLayout);
        rvLayout.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(MemberData.getListData());

        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvLayout.setLayoutManager(new LinearLayoutManager(this));
        CardViewMemberAdapter cardViewMemberAdapter = new CardViewMemberAdapter(this);
        cardViewMemberAdapter.setListMember(list);
        rvLayout.setAdapter(cardViewMemberAdapter);
    }
    public class CardViewMemberAdapter extends RecyclerView.Adapter<CardViewMemberAdapter.CardViewViewHolder>{
        private ArrayList<MemberData.Member> listMember;
        private Context context;

        public CardViewMemberAdapter(Context context) {
            this.context = context;
        }

        public ArrayList<MemberData.Member> getListMember() {
            return listMember;
        }

        public void setListMember(ArrayList<MemberData.Member> listMember) {
            this.listMember = listMember;
        }
        @Override
        public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_member, parent, false);
            CardViewViewHolder viewHolder = new CardViewViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CardViewViewHolder holder, int position) {

            final MemberData.Member p = getListMember().get(position);
            int img = getResources().getIdentifier(p.getPhoto(), null, getPackageName());


            holder.imgPhoto.setImageResource(img);
            holder.tvName.setText(p.getName());
            holder.tvRemarks.setText(p.getRemarks());

            holder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {

                @Override
                public void onItemClicked(View view, int position) {
                    Toast.makeText(context, getListMember().get(position).getName()+" Is your Favourite", Toast.LENGTH_SHORT).show();
                }
            }));

            holder.btnDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {

                @Override
                public void onItemClicked(View view, int position) {
                    Toast.makeText(context, "Detail " + getListMember().get(position).getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("name", getListMember().get(position).getName());
                    intent.putExtra("date", getListMember().get(position).getDOB());
                    intent.putExtra("desc", getListMember().get(position).getDesc());
                    intent.putExtra("imgdetail", getListMember().get(position).getPhotoDetail());
                    context.startActivity(intent);
                }
            }));
        }

        @Override
        public int getItemCount() {
            return getListMember().size();
        }

        public class CardViewViewHolder extends RecyclerView.ViewHolder{
            ImageView imgPhoto;
            TextView tvName, tvRemarks;
            Button btnFavorite, btnDetail;
            public CardViewViewHolder(View itemView) {
                super(itemView);
                imgPhoto = itemView.findViewById(R.id.img_item_photo);
                tvName = itemView.findViewById(R.id.tv_item_name);
                tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
                btnFavorite = itemView.findViewById(R.id.favorite);
                btnDetail = itemView.findViewById(R.id.detail);
            }
        }
    }

    static class CustomOnItemClickListener implements View.OnClickListener {
        private int position;
        private OnItemClickCallback onItemClickCallback;
        public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
            this.position = position;
            this.onItemClickCallback = onItemClickCallback;
        }

        @Override
        public void onClick(View view) {
            onItemClickCallback.onItemClicked(view, position);
        }
        public interface OnItemClickCallback {
            void onItemClicked(View view, int position);

        }
    }
    static class MemberData {
        public static String[][] data = new String[][]{
                {"Chen", "Kim Jong Dae", "@drawable/cbx_chen", "September 21, 1992", "Chen, is a South Korean singer-songwriter and actor. He is a member of the South Korean-Chinese boy group Exo and its sub-unit Exo-CBX, as well as SM Entertainment's ballad group SM the Ballad. He is predominantly known for his role as the main vocalist in Exo.", "@drawable/cbx_chen_detail"},
                {"BaekHyun", "Byun Baek Hyun", "@drawable/cbx_baekhyun", "May 6, 1992", "Byun Baek-hyun, better known mononymously as Baekhyun, is a South Korean singer, songwriter, actor, model. He is a member of the South Korean-Chinese boy group Exo, its sub-group Exo-K and sub-unit Exo-CBX. ", "@drawable/cbx_baekhyun_detail"},
                {"Xiumin", "Kim Min Seok", "@drawable/cbx_xiumin", "March 26, 1990", "Kim Min-seok , better known by his stage name Xiumin (Korean: 시우민; [ɕʰi u min]; shee-oo-meen), is a South Korean singer and actor. He is best known as a member of the South Korean-Chinese boy group Exo, its sub-group Exo-M and its sub-unit Exo-CBX.", "@drawable/cbx_xiumin_detail"},
        };

        public static ArrayList<Member> getListData(){
            Member member = null;
            ArrayList<Member> list = new ArrayList<>();
            for (int i = 0; i <data.length; i++) {
                member = new Member();
                member.setName(data[i][0]);
                member.setRemarks(data[i][1]);
                member.setPhoto(data[i][2]);
                member.setDOB(data[i][3]);
                member.setDesc(data[i][4]);
                member.setPhotoDetail(data[i][5]);

                list.add(member);
            }

            return list;
        }

        public static class Member {
            private String name;
            private String remarks;
            private String photo;
            private String DOB;
            private String describe;
            private String PhotoDetail;

            public String getName()
            {
                return name;
            }

            public void setName(String name)
            {
                this.name = name;
            }

            public String getRemarks()
            {
                return remarks;
            }

            public void setRemarks(String remarks)
            {
                this.remarks = remarks;
            }

            public String getPhoto()
            {
                return photo;
            }

            public void setPhoto(String photo)
            {
                this.photo = photo;
            }

            public String getDOB()
            {
                return DOB;
            }
            public void setDOB(String dob)
            {
                this.DOB = dob;
            }
            public String getDesc()
            {
                return describe;
            }
            public void setDesc(String describ)
            {
                this.describe = describ;
            }
            public String getPhotoDetail()
            {
                return PhotoDetail;
            }

            public void setPhotoDetail(String photodetail)
            {
                this.PhotoDetail = photodetail;
            }
        }
    }

}