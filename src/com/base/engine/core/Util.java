package com.base.engine.core;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import com.base.engine.core.math.Matrix4f;
import com.base.engine.rendering.Vertex;

public class Util {
	public static FloatBuffer arrayToFloatBuffer(float[] array) {
		FloatBuffer buffer = createFloatBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	public static Buffer arrayToIntBuffer(int[] array) {
		IntBuffer buffer = createIntBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer matricesToFloatBuffer(Matrix4f[] matrices) {
		FloatBuffer buffer = createFloatBuffer(4 * 4 * matrices.length);

		for (int k = 0; k < matrices.length; k++) {
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++)
					buffer.put((float) matrices[k].get(i, j));
		}

		buffer.flip();

		return buffer;
	}

	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}

	public static IntBuffer createIntBuffer(int size) {
		return BufferUtils.createIntBuffer(size);
	}

	public static ByteBuffer createByteBuffer(int size) {
		return BufferUtils.createByteBuffer(size);
	}

	public static IntBuffer createFlippedBuffer(int... values) {
		IntBuffer buffer = createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();

		return buffer;
	}

	public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);

		for (int i = 0; i < vertices.length; i++) {
			buffer.put((float) vertices[i].getPos().getX());
			buffer.put((float) vertices[i].getPos().getY());
			buffer.put((float) vertices[i].getPos().getZ());
			buffer.put((float) vertices[i].getTexCoord().getX());
			buffer.put((float) vertices[i].getTexCoord().getY());
			buffer.put((float) vertices[i].getNormal().getX());
			buffer.put((float) vertices[i].getNormal().getY());
			buffer.put((float) vertices[i].getNormal().getZ());
		}

		buffer.flip();

		return buffer;
	}

	public static FloatBuffer createFlippedBuffer(Matrix4f value) {
		FloatBuffer buffer = createFloatBuffer(4 * 4);

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				buffer.put((float) value.get(i, j));

		buffer.flip();

		return buffer;
	}

	public static String[] removeEmptyStrings(String[] data) {
		ArrayList<String> result = new ArrayList<String>();

		for (int i = 0; i < data.length; i++)
			if (!data[i].equals(""))
				result.add(data[i]);

		String[] res = new String[result.size()];
		result.toArray(res);

		return res;
	}

	public static int[] toIntArray(Integer[] data) {
		int[] result = new int[data.length];

		for (int i = 0; i < data.length; i++)
			result[i] = data[i].intValue();

		return result;
	}
}
