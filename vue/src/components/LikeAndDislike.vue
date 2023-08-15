<template>
  <div id="likeButtons" v-show="$store.state.token != ''">
      <div class="likes">
          <p>{{numberOfLikes}}</p>
          <ArrowCircleUpIcon class="like-button" v-show="!isLiked" v-on:click="onClickLike()" />
          <SolidUpArrow class="like-button" v-show="isLiked" v-on:click="onClickLike()"/>
      </div>
      <div class="likes">
          <p>{{numberOfDislikes}}</p>
          <ArrowCircleDownIcon class="like-button" v-show="!isDisliked" v-on:click="onClickDislike()" />
          <SolidDownArrow class="like-button" v-show="isDisliked" v-on:click="onClickDislike()" />
      </div>
  </div>
</template>

<script>
import PostService from '../services/PostService'
import {ArrowCircleUpIcon} from '@vue-hero-icons/outline'
import {ArrowCircleUpIcon as SolidUpArrow} from '@vue-hero-icons/solid'
import {ArrowCircleDownIcon} from '@vue-hero-icons/outline'
import {ArrowCircleDownIcon as SolidDownArrow} from '@vue-hero-icons/solid'
export default {
    components: {ArrowCircleUpIcon, SolidUpArrow, ArrowCircleDownIcon, SolidDownArrow},
    props: [
        'postId',
        'post'
    ],
    data() {
        return {
            isLiked: false,
            isDisliked: false,
            numberOfLikes: 0,
            numberOfDislikes: 0,
        }
    },
    methods: {
        setLike() {
            PostService.likePost(this.postId, this.$store.state.user.id);
            //TRYING TO UPDATE isLiked AND isDisliked. On refresh it does not update setLiked() and setDisliked() even though it is in state.dislikedPosts
            //Also only in this component
            this.$store.commit("ADD_LIKED_POST", this.post)
        },
        setDislike() {
            PostService.dislikePost(this.postId, this.$store.state.user.id);
            this.$store.commit("ADD_DISLIKED_POST", this.post)
            
        },
        removeLike() {
            PostService.removeLikeOnPost(this.postId, this.$store.state.user.id)
        },
        getNumberOfLikes() {
            PostService.getNumberOfLikes(this.postId).then((response) => {
                this.numberOfLikes = response.data
            })
        },
        getNumberOfDislikes() {
            PostService.getNumberOfDislikes(this.postId).then((response) => {
                this.numberOfDislikes = response.data
            })
        },
        onClickLike() {
            if(this.isLiked) {
                this.isLiked = !this.isLiked;
                this.isDisliked = false;
                this.removeLike();
                this.numberOfLikes --
            } else {
                this.isLiked = !this.isLiked;
                if (this.isDisliked) {
                    this.numberOfDislikes --
                    this.isDisliked = false;
                }
                this.setLike();
                this.numberOfLikes ++
            }
        },
        onClickDislike() {
            if(this.isDisliked) {
                this.isDisliked = !this.isDisliked;
                this.isLiked = false;
                this.removeLike();
                this.numberOfDislikes --
            } else {
                this.isDisliked = !this.isDisliked;
                if (this.isLiked) {
                    this.numberOfLikes --
                    this.isLiked = false;
                }
                this.setDislike();
                this.numberOfDislikes ++;
            }
        },
        setLiked() {
            this.$store.state.likedPosts.forEach((post) => {
                if (post.postId === this.postId) {
                    this.isLiked = true;
                }
            })
        },
        setDisliked() {
            this.$store.state.dislikedPosts.forEach((post) => {
                if (post.postId === this.postId) {
                    this.isDisliked = true;
                }
            })
        },
    },
    created() {
        this.getNumberOfLikes();
        this.getNumberOfDislikes();
        this.setLiked();
        this.setDisliked();
    },
}
</script>

<style>
#likeButtons {
    margin-left: 5px;
    text-align: center;
}
.likes {
    display: flex;
    align-items: center;
    margin: 2px;
}

.likes p{
    margin-right: auto;
}

.like-button{
    color: #555597;
    margin-left: 3px;
}

.like-button:hover {
    cursor: pointer;
    color: #978555;
    transition-duration: 250ms;
}

</style>