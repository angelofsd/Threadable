<template>

        <div id="all-list">
            <h3>All Forums</h3>
            <div class="forum" v-for="forum in forums" v-bind:key="forum.id">
                <div id="forum-subheader">
                    <router-link :to="{name: 'forumPage', params: {id: forum.id}}">
                    {{forum.name}}
                    </router-link>
                    <FavoriteButton :forumId="forum.id"></FavoriteButton>
                </div>
                <p> {{forum.description}}</p>
                <h4 class="created-by">
                    <router-link :to="{name:'user', params:{id : forum.createdBy}}">Created By: {{forum.username}}
                    </router-link>
                </h4>
            </div>
        </div>
</template>

<script>
import ForumService from '../services/ForumService.js';
import UserService from '../services/UserService.js';
import FavoriteButton from './FavoriteButton.vue';

export default {
    name:'forum-list',
    components: {FavoriteButton},
    props: {
        forumId: {
            name: "",
            createdBy: "",
            description: "",
            dateCreated: ""
        }
    },
    data() {
        return {
            forums: [],
            username: "",
        }
    },
    methods: {
        getForums() {
            ForumService.list().then((response) => {
                if (response.status === 200) {
                    this.forums=response.data;
                    this.forums.forEach((forum) => {
                        this.getUsername(forum);
                        
                    })
                }
            })
            .catch( (error) => {
                if (error.response) {
                alert("Something went wrong: " + error.response.statusText);
                } else if(error.request){
                            //We could not reach the server
                            alert("We could not reach the server");
                } else {
                            alert("Something went horribly wrong");
                        }
            })
        },
        getUsername(forum) {
            UserService.getUserById(forum.createdBy).then((response) => {
                if(response.status===200) {
                    forum.username = response.data.username
                    this.$forceUpdate();
                    console.log("It works");
                }
            }).catch((error) => {
                console.error("An error occurred", error)
            })
        }
    },
    created() {
        this.getForums();

        
    }
}
</script>

<style scoped>
#all-list {
  margin: 20px;
}

#forum-subheader {
  display: flex;
  justify-content: space-between;
}

.forum {
  border: solid 1px #555597;
  box-shadow: 1px 2px #555597;
  border-radius: 10px;
  margin: 20px 0px;
  padding: 5px;

}

.forum:hover {
  border: solid 1px #978555;
  box-shadow: 1px 2px #978555;
}

.forum p {
  font-size: 14px;
}

.forum a {
  border-bottom: solid 1px #555597;
}

.forum a:hover {
  border-bottom: solid 1px #978555;
}

.created-by {
    display: flex;
    justify-content:end;
    font-weight: normal;
}

</style>