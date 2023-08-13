<template>
  <!-- REMOVE FROM PROFILE WHEN DONE -->
  <div>
      <StarIcon v-show="!isFavorited" v-on:click="onClick()" id="favorite-button" />
      <SolidStar v-show="isFavorited" v-on:click="onClick()" id="favorited-button" />
  </div>
</template>

<script>
import { StarIcon } from '@vue-hero-icons/outline'
import { StarIcon as SolidStar} from '@vue-hero-icons/solid'
import ForumService from '../services/ForumService'
export default {
    components: {
        StarIcon,
        SolidStar
    },
    props: [
        'forumId'
    ],
    data() {
      return {
          isFavorited: false,
      }  
    },
    methods: {
        setFavorited() {
            this.$store.state.favoritedForums.forEach((forum) => {
                if (forum.id === this.forumId) {
                    this.isFavorited = true;
                }
            })
        },
        onClick() {
            if(this.isFavorited) {
                //button / 'local storage'
                this.isFavorited = !this.isFavorited
                //database
                ForumService.removeFavoritedForum(this.forumId, this.$store.state.user.id)
            } else {
                this.isFavorited = !this.isFavorited
                ForumService.setFavoritedForum(this.forumId, this.$store.state.user.id)
            }
        }
    },
    created() {
        this.setFavorited()
    }
}
</script>

<style>

#favorite-button {
    width: 20px;
    color: #555597;
}

#favorited-button {
    color: #555597;
}

#favorite-button:hover {
    cursor: pointer;
    color: #978555;
    transition-duration: 250ms;
}

#favorited-button:hover {
    cursor: pointer;
    color: #978555;
    transition-duration: 250ms;
}

</style>