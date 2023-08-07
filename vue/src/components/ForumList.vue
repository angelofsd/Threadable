<template>
  <div>
  </div>
</template>

<script>
import ForumService from '../services/ForumService.js';

export default {
    name:'forum-list',
    methods: {
        getForums() {
            ForumService.list().then((response) => {
                if (response.status === 200) {
                    this.getForums();
                }
            })
            }
        },
        created() {
            this.getForums();
        }
}
</script>

<style>

</style>