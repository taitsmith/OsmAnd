package net.osmand.plus.settings.vehiclesize;

import static net.osmand.plus.settings.vehiclesize.DimensionType.HEIGHT;
import static net.osmand.plus.settings.vehiclesize.DimensionType.LENGTH;
import static net.osmand.plus.settings.vehiclesize.DimensionType.WEIGHT;
import static net.osmand.plus.settings.vehiclesize.DimensionType.WIDTH;

import android.content.Context;

import androidx.annotation.NonNull;

import net.osmand.plus.R;
import net.osmand.plus.settings.backend.ApplicationMode;
import net.osmand.plus.base.wrapper.Assets;
import net.osmand.plus.base.wrapper.Limits;
import net.osmand.plus.base.wrapper.ThemedIconId;

import java.util.Locale;

public class TruckSizes extends VehicleSizes {

	@Override
	protected void collectDimensionsData() {
		ThemedIconId icon = new ThemedIconId(R.drawable.img_help_width_limit_day, R.drawable.img_help_width_limit_night);
		Assets assets = new Assets(icon, R.string.width_limit_description);
		Limits limits = new Limits(1.7f, 2.5f);
		add(WIDTH, assets, limits);

		icon = new ThemedIconId(R.drawable.img_help_height_limit_day, R.drawable.img_help_height_limit_night);
		assets = new Assets(icon, R.string.height_limit_description);
		limits = new Limits(1.5f, 4.5f);
		add(HEIGHT, assets, limits);

		icon = new ThemedIconId(R.drawable.img_help_length_limit_day, R.drawable.img_help_length_limit_night);
		assets = new Assets(icon, R.string.lenght_limit_description);
		limits = new Limits(4.5f, 12f);
		add(LENGTH, assets, limits);

		icon = new ThemedIconId(R.drawable.img_help_weight_limit_day, R.drawable.img_help_weight_limit_night);
		assets = new Assets(icon, R.string.weight_limit_description);
		limits = new Limits(3.5f, 16f);
		add(WEIGHT, assets, limits);
	}

	@Override
	public boolean verifyValue(@NonNull DimensionType type, @NonNull Context ctx,
	                           float value, @NonNull StringBuilder error) {
		if (type == WEIGHT) {
			DimensionData dimension = getDimensionData(type);
			float min = dimension.getLimits().getMin();
			if (value < min) {
				String errorMessagePattern = ctx.getString(R.string.weight_limit_error);
				String minWeightFormatted = String.format(Locale.US, "%.1f", min);
				String drivingProfileName = ctx.getString(ApplicationMode.CAR.getNameKeyResource());
				String message = String.format(errorMessagePattern, minWeightFormatted, drivingProfileName);
				error.append(message);
				return false;
			}
		}
		return true;
	}
}
