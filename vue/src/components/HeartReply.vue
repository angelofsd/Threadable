<template>
  <div class="heart">
      
      <HeartIcon class="heart-button" v-on:click="onClick()" v-show="!isHearted"/>
      <SolidHeart class="hearted-button" v-on:click="onClick()" v-show="isHearted"/>
      <small>{{numberofHearts}}</small>
  </div>
</template>

<script>
import { HeartIcon } from '@vue-hero-icons/outline'
import { HeartIcon as SolidHeart} from '@vue-hero-icons/solid'
import ReplyService from '../services/ReplyService'
//import { HeartIcon } from 'node_modules\@vue-hero-icons\outline\icons\HeartIcon.js'
export default {
    components: {
        HeartIcon, SolidHeart
    },
    props: [
        'replyId'
    ],
    data() {
      return {
          isHearted: false,
          numberofHearts: 0,
      }  
    },
    methods: {
        setHearted() {
            this.$store.state.heartedReplies.forEach((reply) => {
                if (reply.id === this.replyId) {
                    this.isHearted = true;
                }
            });

        },
        onClick() {
            if(this.isHearted) {
                //button / 'local storage'
                this.isHearted = false
                //database
                ReplyService.removeHearted(1, this.replyId, this.$store.state.user.id)
                this.numberofHearts--;
            } else {
                this.isHearted = true
                ReplyService.setHearted(1, this.replyId, this.$store.state.user.id)
                this.numberofHearts++;
            }
        }
    },
    created() {
        this.setHearted()
    }
}

</script>

<style>

</style>