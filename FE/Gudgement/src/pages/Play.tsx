import { useCallback, useEffect, useState } from "react";
import FastImage, { FastImageProps } from "react-native-fast-image";
import {
  ImageBackground,
  Image,
  ImageSourcePropType,
  Pressable,
  Text,
  View,
} from "react-native";
import Animated, {
  useAnimatedStyle,
  useSharedValue,
  withRepeat,
  withTiming,
} from "react-native-reanimated";

import { NavigationProp, useNavigation } from "@react-navigation/native";
import PlayBackground from "../assets/images/playBackground.png";
import FlameEntrance from "../assets/images/flame.gif";

import { CommonType } from "../types/CommonType";

function Play() {
  const playBackground: ImageSourcePropType =
    PlayBackground as ImageSourcePropType;
  const navigation =
    useNavigation<NavigationProp<CommonType.RootStackParamList>>();

  const offset = useSharedValue(5);

  const animatedStyles = useAnimatedStyle(() => ({
    transform: [{ translateY: offset.value }],
  }));

  useEffect(() => {
    offset.value = withRepeat(
      withTiming(-offset.value, { duration: 300 }),
      -1,
      true,
    );
  }, [offset]);

  return (
    <View className="flex flex-1 w-full h-full">
      <ImageBackground
        source={playBackground}
        resizeMode="cover"
        className="flex-1"
      >
        <View className="">
          <Animated.View style={[animatedStyles]}>
            <Pressable
              className="absolute justify-center items-center"
              onPress={() => navigation.navigate("PlaySelect")}
            >
              <Image
                source={require("../assets/images/flame.gif")} // first way (local)
                width={500}
                height={500}
              />

              <FastImage
                source={require("../assets/images/flame.gif")} // FlameEntrance gif 이미지 사용
                style={{ width: "100%", height: "100%" }} // 사이즈 조절은 필요에 따라 변경하세요.
                resizeMode={FastImage.resizeMode.contain} // resize mode 설정
              />
            </Pressable>

            {/* <FastImage
                source={require("../assets/images/flame.gif")}
                source={FlameEntrance} // FlameEntrance gif 이미지 사용
                style={{ width: '100%', height: '100%' }} // 사이즈 조절은 필요에 따라 변경하세요.
                resizeMode={FastImage.resizeMode.contain} // resize mode 설정
              /> */}
          </Animated.View>
        </View>
      </ImageBackground>
    </View>
  );
}

export default Play;
