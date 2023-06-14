<template>
  <base-layout page-title="All Memories">
    <ion-item router-link="/memories/1">Japan</ion-item>
    <ion-item>Hiking</ion-item>
    <ion-item>Swimming</ion-item>
  </base-layout>
</template>

<script>
import { defineComponent } from "vue";
import { IonList, IonItem } from "@ionic/vue";

export default defineComponent({
  components: {
    IonList,
    IonItem,
  },
});
</script>
