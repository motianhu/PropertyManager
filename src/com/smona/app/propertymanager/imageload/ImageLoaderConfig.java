package com.smona.app.propertymanager.imageload;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.smona.app.propertymanager.R;

public class ImageLoaderConfig {

    public static DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.property_default_icon)
                .showImageForEmptyUri(R.drawable.property_default_icon)
                .showImageOnFail(R.drawable.property_default_icon)
                .cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .build();
        return options;
    }

    public static DisplayImageOptions getFangwuzulinOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.property_fangwuzulin_default)
                .showImageForEmptyUri(R.drawable.property_fangwuzulin_default)
                .showImageOnFail(R.drawable.property_fangwuzulin_default)
                .cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .build();
        return options;
    }

    /**
     * 异步图片加载ImageLoader的初始化操作，在Application中调用此方法
     * 
     * @param context
     *            上下文对象
     * @param cacheDisc
     *            图片缓存到SDCard的目录，只需要传入SDCard根目录下的子目录即可，默认会建立在SDcard的根目录下
     */
    public static void initImageLoader(Context context, String cacheDisc) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                context);
        builder.threadPoolSize(3);
        builder.threadPriority(Thread.NORM_PRIORITY);
        builder.memoryCache(new WeakMemoryCache());
        builder.memoryCacheSizePercentage(60);
        builder.denyCacheImageMultipleSizesInMemory();
        if (null != cacheDisc) {
            builder.discCache(new UnlimitedDiscCache(StorageUtils
                    .getOwnCacheDirectory(context, cacheDisc)));
        }
        builder.discCacheFileNameGenerator(new HashCodeFileNameGenerator());
        builder.defaultDisplayImageOptions(getDefaultOptions());

        ImageLoader.getInstance().init(builder.build());
        ImageLoader.getInstance().handleSlowNetwork(true);
    }
}
