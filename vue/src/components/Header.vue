<template>
  <div id="header">
    <div id="banner">
        <div>
            <router-link id="logo" v-bind:to="{ name: 'hot' }"><span>THREADABLE</span></router-link>
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
        <div id="searchEl">
          <input v-model="searchInput" v-on:keyup.enter="searchPage()" type="text" placeholder="Search..."/>
        </div>
      <div id="main-tabs">
          <div name="hot" >
            <router-link v-bind:to="{name: 'hot'}"><p :class="{ 'active-tab': isActiveHot }">Hot</p></router-link>
          </div>
          <div name="trending" >
              <router-link v-bind:to="{name: 'trendingForums'}"><p :class="{ 'active-tab': isActiveTrending }">Trending</p></router-link>
          </div>
          <div name="favorited" v-show="$store.state.token != ''">
              <router-link v-bind:to="{name: 'favorites'}"><p :class="{ 'active-tab': isActiveFavorite }">Favorited</p></router-link>
          </div>
          <div name="all" >
              <router-link v-bind:to="{name: 'allForums'}"><p :class="{ 'active-tab': isActiveAll }">All Forums</p></router-link>
          </div>
      </div>
    </div>
</template>

<script>

export default {
    data() {
        return{
            searchInput: "",
            isActiveHot: true,
            isActiveTrending: false,
            isActiveAll: false,
            isActiveFavorite: false,
        }
    },
    created() {
        this.setActiveTab();
    },
    watch: {
        $route() {
            // never used watch but seeing if this works, if route changes call method
            this.setActiveTab();
        },
    },
    methods: {
        searchPage() {
            const route = {
                name: 'search',
                params: {input: this.searchInput}
            }
            this.$router.push(route)
            this.searchInput = "";
        },
          setActiveTab() {
            this.isActiveHot = this.$route.name === 'hot';
            this.isActiveTrending = this.$route.name === 'trendingForums';
            this.isActiveFavorite = this.$route.name === 'favorites';
            this.isActiveAll = this.$route.name === 'allForums';
        },
    },
    
}
</script>

<style scoped>
   
   /* General Header Stuff */

    #header {
        border-bottom: solid 2px #889DAE;
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
        background-color: white;
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
    border-bottom: solid 2px transparent;
    cursor: pointer;
  }


  #main-tabs p:hover {
        color:#2626d8;
        border-bottom: solid 2px #2626d8;
        transition-duration: 50ms;
    }

    #main-tabs p:not( :hover ) {
        transition-duration: 250ms;
    }
    /* Default will be 'HOT', but onClick change the class to this */


    .active-tab {
    /* styling for the active tab TESTING*/
    color:#2626d8;
    border-bottom: solid 2px #2626d8;
    
    transition-duration: 250ms;
    
        
}

   
    
</style>