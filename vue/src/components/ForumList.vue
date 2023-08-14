<template>

        <div class="forumList">
            <div class="testDiv" v-for="forum in forums" v-bind:key="forum.id">
                <div id="forum-subheader">
                    <h3 class="forum-name">  
                        <router-link :to="{name: 'forumPage', params: {id: forum.id}}">
                        {{forum.name}}
                        </router-link>
                    </h3>
                        <FavoriteButton :forumId="forum.id"></FavoriteButton>
                </div>
                    <h4 class="forum-description"> {{forum.description}}</h4>
                    <h4 class="created-by">Created By:
                        <router-link :to="{name:'user', params:{id : forum.createdBy}}">{{forum.username}}
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
.forum-subheader {
    display: flex;
}
.testDiv {
    border: solid 2px #889DAE;
    padding-left: 10px;
    padding-right: 10px;
    margin-bottom:10px;
    border-radius: 8px;
    width:75%
    
}
.forum-description {
    display: inline-block;
    width:50%;
    flex-direction: row-reverse;
}

.created-by {
    display: inline-block;
    width:50%;
    text-align:right;
    font-weight: normal;
}

</style>