<template>
  <div id="header">
    <div id="banner">
        <div>
            <router-link id="logo" v-bind:to="{ name: 'home' }"><span>Reddit (Lite)</span></router-link>
        </div>
        <div id="nav">
            <router-link id="profile-pic-link" v-bind:to="{ name: 'user', params: {id: $store.state.user.id}}">
                <div id="circle-pic">
                    <img id="profile-pic" v-show="$store.state.token != ''" v-bind:src="$store.state.user.profilePic" />
                </div>
            </router-link>
            <div id="login-text">
                <p v-show="$store.state.token != ''">You are logged in as <b>{{$store.state.user.username}}</b></p>
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
                <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
            </div>
        </div>
    </div>
      <form id="searchEl">
          <input v-model="searchInput" v-on:keyup.enter="searchPage()" type="text" placeholder="Search..."/>
      </form>
      <div id="main-tabs">
          <div name="hot">
              <router-link v-bind:to="{name: 'hot'}"><p>Hot</p></router-link>
          </div>
          <div name="trending">
              <router-link v-bind:to="{name: 'trending'}"><p>Trending</p></router-link>
          </div>
          <div name="favorited" v-show="$store.state.token != ''">
              <router-link v-bind:to="{name: 'favorites'}"><p>Favorited</p></router-link>
          </div>
      </div>
    </div>
</template>

<script>

export default {
    data() {
        return{
            searchInput: "",
        }
    },
    methods: {
        //Probably works, just need to figure out how to print list
        searchPage() {
            const route = {
                name: 'search',
                params: {input: this.searchInput}
            }

            this.router.push(route)
        }
    }
    
}
</script>

<style scoped>
   
   /* General Header Stuff */

    #header {
        border-bottom: solid 1px #889DAE;
    }

    #banner {
        display: flex;
        justify-content: space-between;
    }

    /* Profile Picture */


    #profile-pic {
        width: 50px;
        border: solid 1px #555597;
        border-radius: 50%;
    }

    #profile-pic:hover {
        transition-duration: 250ms;
        cursor: pointer;
        box-shadow: 2px 3px #555597;
    }

    #profile-pic-link:hover {
        border-bottom:none; /* Overwrites a:hover in App.vue */
    }

    /* Login, Logout, and Profile Nav */
    
    #nav {
        text-align: right;
    }

    /* Logo */

    #header span {
        font-family: 'Unica One', 'Montserrat', sans-serif;
        font-size: 28px;
        cursor: pointer;
        border-radius: 5px;
        border-bottom: none;
        margin-bottom: 15px;
    }

    #header span:hover {
        color:rgb(85, 85, 151);
        transition-duration: 250ms;
        box-shadow: 2px 2px 1px 1px #555597;
    }

    #header span:not( :hover ) {
        transition-duration: 250ms;
    }

    #logo {
        text-decoration: none;
        color: inherit;
        border-bottom: none;
    }

    /* Search Bar */

    #searchEl {
        text-align: center;
    }

    /* Hot, Trending, and Favorited Tabs */

    #main-tabs {
        margin-left: 40px;
        display: flex;
        gap: 50px;
    }

    #main-tabs p {
    padding: 1px;
    border-bottom: solid 1px transparent;
    cursor: pointer;
  }


  #main-tabs p:hover {
        color:#555597;
        border-bottom: solid 1px #555597;
        transition-duration: 250ms;
    }

    #main-tabs p:not( :hover ) {
        transition-duration: 250ms;
    }
    /* Default will be 'HOT', but onClick change the class to this */
    .clicked {
        color:#555597;
        border-bottom: solid 1px #555597;
        transition-duration: 250ms;
    }
    
</style>