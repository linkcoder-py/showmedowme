<template>
  <div class="preparation-environment">
    <h2 class="ready-title">장보기 준비</h2>
    <small>설정을 원하지 않으면 SKIP을 눌러주세요.</small>
    <v-row class="mt-13">
      <v-col cols="4" class="preparation-type">
        <h3>목표시간</h3>
      </v-col>
      <v-col cols="8">
        <v-slider
          v-model="setData.value"
          :thumb-size="40"
          thumb-label="always"
          step="30"
          v-bind:max="240"
          :rules="rules"
          thumb-color="teal"
          track-color="teal lighten-4"
        >
          <template v-slot:thumb-label="{ value }">
            {{ ticksLabels[Math.floor(value / 30)] }}
          </template>
        </v-slider>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <h5 v-if="setData.isSkipTime && setData.value === 0">
          시간은 넉넉하게 하겠습니다.
        </h5>
      </v-col>
      <div class="btn-skip" v-if="setData.isSkipTime && setData.value === 0">
        <v-btn small outlined color="teal lighten-2">SKIP</v-btn>
      </div>
      <div v-else class="btn-skip">
        <v-btn small outlined @click="skipAimTime()"> skip </v-btn>
      </div>
    </v-row>
    <hr class="divide-line mb-5 mt-2" />

    <v-row>
      <v-col cols="4" class="preparation-type">
        <h3>목표금액</h3>
      </v-col>
      <v-col cols="5">
        <v-text-field
          type="text"
          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
          v-model="setData.amount"
          outlined
          dense
        ></v-text-field>
      </v-col>
      <v-col cols="2" class="preparation-type"><h3>원</h3></v-col>
    </v-row>
    <v-row>
      <v-col>
        <h5 v-if="setData.isSkipMoney && setData.amount === ''">
          금액은 상관없습니다.
        </h5>
      </v-col>
      <div class="btn-skip" v-if="setData.isSkipMoney && setData.amount === ''">
        <v-btn small outlined color="teal lighten-2">SKIP</v-btn>
      </div>
      <div v-else class="btn-skip">
        <v-btn small outlined @click="skipAimValue()"> skip </v-btn>
      </div>
    </v-row>
    <v-btn
      large
      class="ma-10 m-3 0 4 set-btn"
      @click="setShop(setData)"
      color="teal lighten-2"
      :disabled="
        !(
          (setData.isSkipMoney || setData.amount) &&
          (setData.isSkipTime || setData.value)
        )
      "
    >
      <h3>장보러 가기</h3>
    </v-btn>
  </div>
</template>

<script>
import "@/assets/css/components/Preparation/preparationEnvironment.scss";
import { mapActions } from "vuex";

export default {
  name: "PreparationEnvironment",
  created() {
    let today = new Date();
    this.hours = today.getHours();
    this.minute = today.getMinutes();
    this.time = this.hour + ":" + this.minute + ":00";
  },
  methods: {
    ...mapActions(["setShop"]),
    skipAimTime() {
      this.setData.isSkipTime = true;
      this.setData.value = 0;
    },
    skipAimValue() {
      this.setData.isSkipMoney = true;
      this.setData.amount = "";
    },
  },
  data() {
    return {
      number: "",
      hour: "",
      minute: "",
      time: "",
      setData: {
        amount: "", // 목표 금액
        value: 0, // 목표 시간
        isSkipTime: false,
        isSkipMoney: false,
      },
      rules: [(v) => v <= 120 || "과도한 쇼핑은 건강에 해롭습니다."],
      ticksLabels: [
        "0",
        "30m",
        "1h",
        "1h30m",
        "2h",
        "2h30m",
        "3h",
        "3h30m",
        "4h",
      ],
    };
  },
};
</script>