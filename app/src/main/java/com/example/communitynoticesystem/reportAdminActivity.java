package com.example.communitynoticesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reportAdminActivity extends AppCompatActivity {

    private RecyclerView postList;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef,PostsRef,ReportRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_admin);

        mAuth = FirebaseAuth.getInstance();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        ReportRef = FirebaseDatabase.getInstance().getReference().child("Reports");
        postList = (RecyclerView) findViewById(R.id.all_users_postlist1);
        postList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        postList.setLayoutManager(linearLayoutManager);

        DisplayAllUserPosts();


    }

    private void DisplayAllUserPosts() {
        FirebaseRecyclerAdapter<Posts, PostsViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Posts, PostsViewHolder>(
                        Posts.class,
                        R.layout.all_post_layout,
                        PostsViewHolder.class,
                        ReportRef
                ) {
                    @Override
                    protected void populateViewHolder(PostsViewHolder postsViewHolder, Posts posts, int i) {
                        final String PostKey = getRef(i).getKey();
                        postsViewHolder.setName(posts.getName());
                        postsViewHolder.setTime(posts.getTime());
                        postsViewHolder.setDate(posts.getDate());
                        postsViewHolder.setDescription(posts.getDescription());
                        postsViewHolder.setLocation(posts.getLocation());


                        postsViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent clickPostIntent = new Intent (reportAdminActivity.this,clickPostActivity.class);
                                clickPostIntent.putExtra("PostKey",PostKey);
                                startActivity(clickPostIntent);

                            }
                        });

                    }
                };

        postList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class  PostsViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public PostsViewHolder(@NonNull View itemView) {

            super(itemView);
            mView = itemView;
        }

        public void setName(String name){
            TextView username = (TextView) mView.findViewById(R.id.post_user_name);
            username.setText(name);

        }

        public void setTime(String time){
            TextView post_time = (TextView) mView.findViewById(R.id.post_time);
            post_time.setText("   "+time);
        }

        public void setDate(String date){
            TextView post_date = (TextView) mView.findViewById(R.id.post_date);
            post_date.setText("   "+date);
        }

        public void setDescription(String description){
            TextView post_description2= (TextView) mView.findViewById(R.id.post_description1);
            post_description2.setText(description);

        }

        public void setLocation(String location){
            TextView post_location = (TextView) mView.findViewById(R.id.post_location);
            post_location.setText(location);

        }



    }
}
