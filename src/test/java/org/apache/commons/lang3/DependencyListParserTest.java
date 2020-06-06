package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DependencyListParserTest {

	@Test
	void testParseDependencyList() throws IOException {
		//
		Assertions.assertNull(DependencyListParser.parseDependencyList(null));
		//
		try (final InputStream is = new ByteArrayInputStream(
				"   org.apache.commons:commons-lang3:jar:3.10:compile".getBytes())) {
			//
			final List<?> result = DependencyListParser.parseDependencyList(is);
			//
			Assertions.assertNotNull(result);
			Assertions.assertSame(1, result.size());
			Assertions.assertEquals(
					"DependencyListParser.Dependency[artifactId=commons-lang3,groupId=org.apache.commons,scope=compile,type=jar,version=3.10]",
					ToStringBuilder.reflectionToString(result.get(0), ToStringStyle.SHORT_PREFIX_STYLE));
			//
		} // try
			//
	}

}